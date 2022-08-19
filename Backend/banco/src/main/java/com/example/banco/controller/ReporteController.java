package com.example.banco.controller;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.service.IReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private IReporteService reporteService;

    @GetMapping
    public ResponseEntity<PaginaDTO<ClienteDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer numeroPagina,
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina,
            @RequestParam(value = "fecha_inicio", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fecha_fin", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin
    ) {

        PaginaDTO<ClienteDTO> paginaClientes;
        //paginaClientes = reporteService.getReporteByFecha(numeroPagina, tamanioPagina, fechaInicio, fechaFin);
        //return new ResponseEntity<>(paginaClientes, HttpStatus.OK);
        return  null;
    }
}
