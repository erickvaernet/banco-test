package com.example.banco.controller;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.service.IClienteService;
import com.example.banco.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private IReporteService reporteService;

    @GetMapping
    public ResponseEntity<PaginaDTO<ClienteDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            @RequestParam(value = "fecha_inicio", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(value = "fecha_fin", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin
    ) {

        PaginaDTO<ClienteDTO> paginaClientes;
        paginaClientes = reporteService.getReporteByFecha(page, size, fechaInicio, fechaFin);
        return new ResponseEntity<>(paginaClientes, HttpStatus.OK);
    }
}
