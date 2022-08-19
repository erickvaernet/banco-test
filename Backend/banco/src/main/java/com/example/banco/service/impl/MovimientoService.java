package com.example.banco.service.impl;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.exception.EntityNotFoundException;
import com.example.banco.exception.InvalidIdException;
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
        Movimiento movimiento = mapToEntity(movimientoDTO);
        Movimiento newMovimiento = movimientoRepository.save(movimiento);
        return mapToDTO(newMovimiento);
    }

    @Override
    public MovimientoDTO updateMovimiento(MovimientoDTO movimientoDTO) {
        Movimiento movimiento = mapToEntity(movimientoDTO);
        Movimiento movimientoActualizada = movimientoRepository.save(movimiento);
        movimientoDTO.setId(movimientoActualizada.getId());
        return movimientoDTO;
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
        return null;
    }
    
    private MovimientoDTO mapToDTO(Movimiento movimiento) {
        return objectMapper.convertValue(movimiento, MovimientoDTO.class);
    }
    private Movimiento mapToEntity(MovimientoDTO movimientoDTO) {
        return objectMapper.convertValue(movimientoDTO, Movimiento.class);
    }
}
