package com.example.banco.service;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;

public interface IMovimientoService {
    MovimientoDTO createMovimiento(MovimientoDTO createMovimientoDTO);

    MovimientoDTO updateMovimientoPUT(Integer id,MovimientoDTO updateMovimientoDTO);

    MovimientoDTO updateMovimientoPATCH(Integer id,MovimientoDTO updateMovimientoDTO);

    MovimientoDTO findMovimientoById(Integer id);

    void deleteMovimientoById(Integer id);

    PaginaDTO<MovimientoDTO> findAllMovimientos(Integer numeroPagina, Integer tamanioPagina);
}
