package com.example.banco.service.impl;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.exception.ClientIllegalArgumentException;
import com.example.banco.exception.EntityNotFoundException;
import com.example.banco.exception.InvalidIdException;
import com.example.banco.model.Movimiento;
import com.example.banco.model.enums.TIpoMovimientoEnum;
import com.example.banco.model.enums.TipoCuentasEnum;
import com.example.banco.repository.IMovimientoRepository;
import com.example.banco.service.ICuentaService;
import com.example.banco.service.IMovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovimientoService implements IMovimientoService {

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro el movimiento con el id indicado";
    private final IMovimientoRepository movimientoRepository;
    private final ICuentaService cuentaService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovimientoService(IMovimientoRepository movimientoRepository, ICuentaService cuentaService, ObjectMapper objectMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaService = cuentaService;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public MovimientoDTO createMovimiento(MovimientoDTO createMovimientoDTO) {
        CuentaDTO cuenta = cuentaService.findCuentaById(createMovimientoDTO.getCuenta().getNumeroCuenta());
        createMovimientoDTO.setSaldoInicial(cuenta.getSaldo());
        if(createMovimientoDTO.getTipo().equals(TIpoMovimientoEnum.DEPOSITO))
            cuenta.setSaldo(cuenta.getSaldo() + createMovimientoDTO.getValor());
        else if(createMovimientoDTO.getTipo().equals(TIpoMovimientoEnum.RETIRO)){
            controlarSaldoDisponible(createMovimientoDTO,cuenta);
            cuenta.setSaldo(cuenta.getSaldo() - createMovimientoDTO.getValor());
        }
        cuentaService.updateCuentaPatch(cuenta.getNumeroCuenta(),cuenta);
        createMovimientoDTO.setSaldoDisponible(cuenta.getSaldo());
        Movimiento movimiento = mapToEntity(createMovimientoDTO);
        Movimiento newMovimiento = movimientoRepository.save(movimiento);
        return mapToDTO(newMovimiento);
    }


    @Override
    @Transactional
    public MovimientoDTO updateMovimientoPUT(Integer id,MovimientoDTO updateMovimientoDTO) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Movimiento movimientoAnterior = movimientoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));

        //cambiar saldos en cuentas del movimiento que sera reemplazado
        CuentaDTO cuentaAnterior = cuentaService.findCuentaById(movimientoAnterior.getCuenta().getNumeroCuenta());
        if(movimientoAnterior.getTipo().equals(TIpoMovimientoEnum.DEPOSITO)){
            cuentaAnterior.setSaldo(cuentaAnterior.getSaldo() - movimientoAnterior.getValor());
        }
        else if (movimientoAnterior.getTipo().equals(TIpoMovimientoEnum.RETIRO)){
            cuentaAnterior.setSaldo(cuentaAnterior.getSaldo() + movimientoAnterior.getValor());
        }
        cuentaService.updateCuentaPatch(cuentaAnterior.getNumeroCuenta(),cuentaAnterior);

        return createMovimiento(updateMovimientoDTO);
    }


    @Override
    @Transactional
    public MovimientoDTO updateMovimientoPATCH(Integer id,MovimientoDTO updateMovimientoDTO) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MovimientoService.ENTITY_NOT_FOUND_MESSAGE));
        CuentaDTO cuenta = cuentaService.findCuentaById(updateMovimientoDTO.getCuenta().getNumeroCuenta());
        if(updateMovimientoDTO.getCuenta()!=null) movimiento.setCuenta(updateMovimientoDTO.getCuenta());
        if (updateMovimientoDTO.getFecha()!=null) movimiento.setFecha(updateMovimientoDTO.getFecha());
        if (updateMovimientoDTO.getSaldoInicial()!=null) movimiento.setSaldoInicial(updateMovimientoDTO.getSaldoInicial());
        if (updateMovimientoDTO.getTipo()!=null) movimiento.setTipo(updateMovimientoDTO.getTipo());
        if (updateMovimientoDTO.getValor()!=null){
            if(!updateMovimientoDTO.getTipo().equals(movimiento.getTipo())){
                if(updateMovimientoDTO.getTipo().equals(TIpoMovimientoEnum.DEPOSITO))
                    cuenta.setSaldo(cuenta.getSaldo() + updateMovimientoDTO.getValor() + movimiento.getValor());
                else if(updateMovimientoDTO.getTipo().equals(TIpoMovimientoEnum.RETIRO)){
                    controlarSaldoDisponible(updateMovimientoDTO,movimiento,cuenta);
                    cuenta.setSaldo(cuenta.getSaldo() - updateMovimientoDTO.getValor() - movimiento.getValor());
                }
            }
            else{
                movimiento.setValor(updateMovimientoDTO.getValor());
                cuenta.setSaldo(cuenta.getSaldo() - updateMovimientoDTO.getValor() - movimiento.getValor());
            }
        }

        movimiento.setSaldoDisponible(movimiento.getSaldoInicial()-movimiento.getValor());
        Movimiento movimientoActualizado = movimientoRepository.save(movimiento);
        return mapToDTO(movimientoActualizado);
    }

    @Override
    public MovimientoDTO findMovimientoById(Integer id) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MovimientoService.ENTITY_NOT_FOUND_MESSAGE));
        return mapToDTO(movimiento);
    }

    @Override
    public void deleteMovimientoById(Integer id) {
        findMovimientoById(id);
        movimientoRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<MovimientoDTO> findAllMovimientos(Integer numeroPagina, Integer tamanioPagina) {
        numeroPagina = numeroPagina==null? 0 : numeroPagina;
        tamanioPagina = tamanioPagina==null? 10 : tamanioPagina;
        Pageable numeroPaginaRequest = PageRequest.of(numeroPagina,tamanioPagina);
        Page<Movimiento> paginaMovimientos = movimientoRepository.findAll(numeroPaginaRequest);
        List<MovimientoDTO> listaMovimientosDTO = paginaMovimientos.getContent().stream().map(this::mapToDTO).toList();
        long cantidad  = paginaMovimientos.getTotalElements();
        return new PaginaDTO<>(numeroPagina , tamanioPagina , cantidad, listaMovimientosDTO);
    }

    private void controlarSaldoDisponible(MovimientoDTO movimientoDTO,CuentaDTO cuenta){
        if( cuenta.getTipo().equals(TipoCuentasEnum.AHORRO) &&
                cuenta.getSaldo() < movimientoDTO.getValor()){
            throw new ClientIllegalArgumentException("No se puede realizar el movimiento, no hay saldo suficiente");
        }
    }
    private void controlarSaldoDisponible(MovimientoDTO movimientoDTO,Movimiento movimiento,CuentaDTO cuenta){
        Double valorTotal= movimiento.getValor()+movimientoDTO.getValor();
        if( cuenta.getTipo().equals(TipoCuentasEnum.AHORRO) &&
                cuenta.getSaldo() < valorTotal){
            throw new ClientIllegalArgumentException("No se puede realizar el movimiento, no hay saldo suficiente");
        }
    }
    
    private MovimientoDTO mapToDTO(Movimiento movimiento) {
        return objectMapper.convertValue(movimiento, MovimientoDTO.class);
    }
    private Movimiento mapToEntity(MovimientoDTO movimientoDTO) {
        return objectMapper.convertValue(movimientoDTO, Movimiento.class);
    }




}
