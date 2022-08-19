package com.example.banco.service;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;

public interface ICuentaService {
    CuentaDTO createCuenta(CuentaDTO cuentaDTO);
    CuentaDTO updateCuenta(CuentaDTO cuentaDTO);
    CuentaDTO findCuentaById(Integer id);
    void deleteCuentaById(Integer id);
    PaginaDTO<CuentaDTO> findAllCuentas(Integer numeroPagina, Integer tamanioPagina);
}
