package com.example.banco.service;



import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;

public interface ICuentaService {
    CuentaDTO createCuenta(CuentaDTO createCuentaDTO);
    CuentaDTO updateCuentaPatch(Integer numeroCuenta,CuentaDTO cuentaDTO);
    CuentaDTO updateCuentaPut(Integer numeroCuenta,CuentaDTO cuentaDTO);
    CuentaDTO findCuentaById(Integer numeroCuenta);
    void deleteCuentaById(Integer numeroCuenta);
    PaginaDTO<CuentaDTO> findAllCuentas(Integer numeroPagina, Integer tamanioPagina);
}
