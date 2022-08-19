package com.example.banco.controller;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<CuentaDTO> create(@RequestBody CuentaDTO cuentadto){
        CuentaDTO respuestaCuenta = cuentaService.createCuenta(cuentadto);
        return new ResponseEntity<>(respuestaCuenta, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<CuentaDTO> update(@RequestBody CuentaDTO cuentaDTO){
        CuentaDTO newCuentaDTO = cuentaService.updateCuenta(cuentaDTO);
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
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina,
            HttpServletRequest request
    ) {

        PaginaDTO<CuentaDTO> paginaCuentas;
        paginaCuentas = cuentaService.findAllCuentas(numeroPagina, tamanioPagina);

        String url= request.getRequestURL().toString();
        return new ResponseEntity<>(paginaCuentas, HttpStatus.OK);
    }
}
