package com.example.banco.controller;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> findById(@PathVariable("id")Integer id){
        CuentaDTO cuentabuscada = cuentaService.findCuentaById(id);
        return new ResponseEntity<>(cuentabuscada, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CuentaDTO> create(@RequestBody CuentaDTO createCuentaDTO){
        CuentaDTO respuestaCuenta = cuentaService.createCuenta(createCuentaDTO);
        return new ResponseEntity<>(respuestaCuenta, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<CuentaDTO> update(@RequestBody CuentaDTO updateCuentaDTO){
        CuentaDTO newCuentaDTO = cuentaService.updateCuenta(updateCuentaDTO);
        return new ResponseEntity<>(newCuentaDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        cuentaService.deleteCuentaById(id);
        return new ResponseEntity<>("Cuenta Eliminado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<CuentaDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer numeroPagina,
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina
    ) {
        PaginaDTO<CuentaDTO> paginaCuentas;
        paginaCuentas = cuentaService.findAllCuentas(numeroPagina, tamanioPagina);
        return new ResponseEntity<>(paginaCuentas, HttpStatus.OK);
    }
}
