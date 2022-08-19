package com.example.banco.service.impl;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.model.Movimiento;
import com.example.banco.repository.IMovimientoRepository;
import com.example.banco.service.IMovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService implements IMovimientoService {

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro el movimiento con el id indicado";
    private final IMovimientoRepository movimientoRepository;
    private final ObjectMapper objectMapper;

    public MovimientoService(IMovimientoRepository movimientoRepository, ObjectMapper objectMapper) {
        this.movimientoRepository = movimientoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO) {
        return null;
    }

    @Override
    public MovimientoDTO updateMovimiento(MovimientoDTO movimientoDTO) {
        return null;
    }

    @Override
    public MovimientoDTO findMovimientoById(Integer id) {
        return null;
    }

    @Override
    public void deleteMovimientoById(Integer id) {

    }

    @Override
    public PaginaDTO<MovimientoDTO> findAllMovimientos(Integer page, Integer size) {
        return null;
    }
    
    private MovimientoDTO mapToDTO(Movimiento movimiento) {
        return objectMapper.convertValue(movimiento, MovimientoDTO.class);
    }
    private Movimiento mapToEntity(MovimientoDTO movimientoDTO) {
        return objectMapper.convertValue(movimientoDTO, Movimiento.class);
    }
}
