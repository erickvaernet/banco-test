package com.example.banco.service;

import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.ReporteDTO;

import java.time.LocalDate;

public interface IReporteService {
   PaginaDTO<ReporteDTO> getReporteByClienteIdAndFecha(LocalDate fechaInicio, LocalDate fechaFin
            , Integer numeroPagina, Integer tamanioPagina);
}