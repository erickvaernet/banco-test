package com.example.banco.service.impl;

import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.ReporteDTO;
import com.example.banco.repository.IMovimientoRepository;
import com.example.banco.repository.IReporteRepository;
import com.example.banco.service.IReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class ReporteService implements IReporteService {

    private final IReporteRepository reporteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReporteService(IReporteRepository reporteRepository, ObjectMapper objectMapper) {
        this.reporteRepository = reporteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PaginaDTO<ReporteDTO> getReporteByFecha(Integer page, Integer size, LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }
}
