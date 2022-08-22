package com.example.banco.controller;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.validationinterface.CreateCuenta;
import com.example.banco.dto.validationinterface.UpdateCuenta;
import com.example.banco.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> findById(@PathVariable("numeroCuenta")Integer numeroCuenta){
        CuentaDTO cuentabuscada = cuentaService.findCuentaById(numeroCuenta);
        return new ResponseEntity<>(cuentabuscada, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CuentaDTO> create(@RequestBody @Validated(CreateCuenta.class) CuentaDTO createCuentaDTO){
        CuentaDTO respuestaCuenta = cuentaService.createCuenta(createCuentaDTO);
        return new ResponseEntity<>(respuestaCuenta, HttpStatus.OK);
    }


    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> updatePut(@PathVariable("numeroCuenta") Integer numeroCuenta ,
                                            @RequestBody @Validated(UpdateCuenta.class) CuentaDTO updateCuentaDTO){
        CuentaDTO newCuentaDTO = cuentaService.updateCuentaPut(numeroCuenta,updateCuentaDTO);
        return new ResponseEntity<>(newCuentaDTO, HttpStatus.OK);
    }

    @PatchMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> updatePatch(@PathVariable("numeroCuenta") Integer numeroCuenta ,
                                            @RequestBody @Validated(UpdateCuenta.class) CuentaDTO updateCuentaDTO){
        CuentaDTO newCuentaDTO = cuentaService.updateCuentaPatch(numeroCuenta,updateCuentaDTO);
        return new ResponseEntity<>(newCuentaDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<String> deleteById(@PathVariable("numeroCuenta")Integer numeroCuenta){
        cuentaService.deleteCuentaById(numeroCuenta);
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
