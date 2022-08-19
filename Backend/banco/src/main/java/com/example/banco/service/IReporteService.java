package com.example.banco.service;

import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.ReporteDTO;

import java.time.LocalDate;
import java.util.List;

public interface IReporteService {
   PaginaDTO<ReporteDTO> getReporteByFecha(Integer page, Integer size, LocalDate fechaInicio, LocalDate fechaFin);
}
