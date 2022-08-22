package com.example.banco.service;



import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;

public interface ICuentaService {
    CuentaDTO createCuenta(CuentaDTO createCuentaDTO);
    CuentaDTO updateCuenta(Integer numeroCuenta,CuentaDTO updateCuentaDTO);
    CuentaDTO findCuentaById(Integer numeroCuenta);
    void deleteCuentaById(Integer numeroCuenta);
    PaginaDTO<CuentaDTO> findAllCuentas(Integer numeroPagina, Integer tamanioPagina);
}
