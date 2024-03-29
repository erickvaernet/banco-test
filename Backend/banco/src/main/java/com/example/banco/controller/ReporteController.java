package com.example.banco.controller;

import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.ReporteDTO;
import com.example.banco.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private IReporteService reporteService;

    @GetMapping
    public ResponseEntity<PaginaDTO<ReporteDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer numeroPagina,
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina,
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        if(fechaInicio==null) fechaInicio=LocalDate.MIN;
        if(fechaFin==null) fechaFin=LocalDate.now();
        PaginaDTO<ReporteDTO> paginaReportes = reporteService.getReporteByClienteIdAndFecha(fechaInicio, fechaFin,numeroPagina, tamanioPagina);
        return new ResponseEntity<>(paginaReportes, HttpStatus.OK);
    }


}
