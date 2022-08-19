package com.example.banco.service.impl;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.model.Cuenta;
import com.example.banco.repository.ICuentaRepository;
import com.example.banco.service.ICuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService implements ICuentaService {

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la cuenta con el id indicado";
    private final ICuentaRepository cuentaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CuentaService(ICuentaRepository cuentaRepository, ObjectMapper objectMapper) {
        this.cuentaRepository = cuentaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CuentaDTO createCuenta(CuentaDTO CuentaDTO) {
        return null;
    }

    @Override
    public CuentaDTO updateCuenta(CuentaDTO CuentaDTO) {
        return null;
    }

    @Override
    public CuentaDTO findCuentaById(Integer id) {
        return null;
    }

    @Override
    public void deleteCuentaById(Integer id) {

    }

    @Override
    public PaginaDTO<CuentaDTO> findAllCuentas(Integer page, Integer size) {
        return null;
    }

    private CuentaDTO mapToDTO(Cuenta cuenta) {
        return objectMapper.convertValue(cuenta, CuentaDTO.class);
    }
    private Cuenta mapToEntity(CuentaDTO cuentaDTO) {
        return objectMapper.convertValue(cuentaDTO, Cuenta.class);
    }
}
