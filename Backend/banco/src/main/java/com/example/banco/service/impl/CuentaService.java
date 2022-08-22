package com.example.banco.service.impl;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.exception.EntityNotFoundException;
import com.example.banco.exception.InvalidIdException;
import com.example.banco.model.Cuenta;
import com.example.banco.repository.ICuentaRepository;
import com.example.banco.service.ICuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService implements ICuentaService {

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la cuenta con el numero de cuenta indicado";
    private final ICuentaRepository cuentaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CuentaService(ICuentaRepository cuentaRepository, ObjectMapper objectMapper) {
        this.cuentaRepository = cuentaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CuentaDTO createCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = mapToEntity(cuentaDTO);
        Cuenta newCuenta = cuentaRepository.save(cuenta);
        return mapToDTO(newCuenta);
    }

    @Override
    public CuentaDTO updateCuentaPatch(Integer numeroCuenta,CuentaDTO cuentaDTO) {
        if(numeroCuenta==null || numeroCuenta <= 0) throw new InvalidIdException();
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta)
                .orElseThrow(()-> new EntityNotFoundException(CuentaService.ENTITY_NOT_FOUND_MESSAGE));
        //Puede reemplazarse usando reflection pero genera menor performance
        if(cuentaDTO.getCliente() != null) cuenta.setCliente(cuentaDTO.getCliente());
        if(cuentaDTO.getSaldo() != null) cuenta.setSaldo(cuentaDTO.getSaldo());
        if(cuentaDTO.getTipo()!= null) cuenta.setTipo(cuentaDTO.getTipo());
        if (cuentaDTO.getEstado() != null) cuenta.setEstado(cuentaDTO.getEstado());
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);
        return mapToDTO(cuentaActualizada);
    }

    @Override
    public CuentaDTO updateCuentaPut(Integer numeroCuenta,CuentaDTO cuentaDTO) {
        if(numeroCuenta==null || numeroCuenta <= 0) throw new InvalidIdException();
        cuentaRepository.findById(numeroCuenta)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        cuentaRepository.save(mapToEntity(cuentaDTO));
        cuentaDTO.setNumeroCuenta(numeroCuenta);
        return cuentaDTO;
    }

    @Override
    public CuentaDTO findCuentaById(Integer id) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if(cuenta.isEmpty()) throw new EntityNotFoundException(CuentaService.ENTITY_NOT_FOUND_MESSAGE);
                //.orElseThrow(()-> new EntityNotFoundException(CuentaService.ENTITY_NOT_FOUND_MESSAGE));
        return mapToDTO(cuenta.get());
    }

    @Override
    public void deleteCuentaById(Integer id) {
        findCuentaById(id);
        cuentaRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<CuentaDTO> findAllCuentas(Integer numeroPagina, Integer tamanioPagina) {
        numeroPagina = numeroPagina==null? 0 : numeroPagina;
        tamanioPagina = tamanioPagina==null? 10 : tamanioPagina;
        Pageable numeroPaginaRequest = PageRequest.of(numeroPagina,tamanioPagina);
        Page<Cuenta> paginaCuentas = cuentaRepository.findAll(numeroPaginaRequest);
        List<CuentaDTO> listaCuentasDTO = paginaCuentas.getContent().stream().map(this::mapToDTO).toList();
        long cantidad  = paginaCuentas.getTotalElements();
        return new PaginaDTO<>(numeroPagina , tamanioPagina , cantidad, listaCuentasDTO);
    }

    private CuentaDTO mapToDTO(Cuenta cuenta) {
        return objectMapper.convertValue(cuenta, CuentaDTO.class);
    }
    private Cuenta mapToEntity(CuentaDTO cuentaDTO) {
        return objectMapper.convertValue(cuentaDTO, Cuenta.class);
    }
}
