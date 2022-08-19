package com.example.banco.service;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;

public interface IMovimientoService {
    MovimientoDTO createMovimiento(MovimientoDTO movimientoDTO);
    MovimientoDTO updateMovimiento(MovimientoDTO movimientoDTO);
    MovimientoDTO findMovimientoById(Integer id);
    void deleteMovimientoById(Integer id);
    PaginaDTO<MovimientoDTO> findAllMovimientos(Integer page, Integer size);
}
