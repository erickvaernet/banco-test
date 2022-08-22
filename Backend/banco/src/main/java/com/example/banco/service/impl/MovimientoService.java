package com.example.banco.service.impl;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.exception.EntityNotFoundException;
import com.example.banco.exception.InvalidIdException;
import com.example.banco.model.Movimiento;
import com.example.banco.repository.IMovimientoRepository;
import com.example.banco.service.IMovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService implements IMovimientoService {

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro el movimiento con el id indicado";
    private final IMovimientoRepository movimientoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovimientoService(IMovimientoRepository movimientoRepository, ObjectMapper objectMapper) {
        this.movimientoRepository = movimientoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public MovimientoDTO createMovimiento(MovimientoDTO createMovimientoDTO) {
        Movimiento movimiento = mapToEntity(createMovimientoDTO);
        Movimiento newMovimiento = movimientoRepository.save(movimiento);
        return mapToDTO(newMovimiento);
    }

    @Override
    public MovimientoDTO updateMovimiento(Integer id,MovimientoDTO updateMovimientoDTO) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MovimientoService.ENTITY_NOT_FOUND_MESSAGE));
        //Puede reemplazarse usando reflection pero genera menor performance
        if(updateMovimientoDTO.getCuenta()!=null) movimiento.setCuenta(updateMovimientoDTO.getCuenta());
        if (updateMovimientoDTO.getFecha()!=null) movimiento.setFecha(updateMovimientoDTO.getFecha());
        if (updateMovimientoDTO.getValor()!=null) movimiento.setValor(updateMovimientoDTO.getValor());
        if (updateMovimientoDTO.getSaldo()!=null) movimiento.setSaldo(updateMovimientoDTO.getSaldo());
        if (updateMovimientoDTO.getTipo()!=null) movimiento.setTipo(updateMovimientoDTO.getTipo());
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
    
    private MovimientoDTO mapToDTO(Movimiento movimiento) {
        return objectMapper.convertValue(movimiento, MovimientoDTO.class);
    }
    private Movimiento mapToEntity(MovimientoDTO movimientoDTO) {
        return objectMapper.convertValue(movimientoDTO, Movimiento.class);
    }
}
