package com.example.banco.service.impl;


import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.ReporteDTO;
import com.example.banco.repository.IMovimientoRepository;
import com.example.banco.repository.projections.IReporteProjection;
import com.example.banco.service.IReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteService implements IReporteService {

    private final IMovimientoRepository movimientoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReporteService(IMovimientoRepository movimientoRepository, ObjectMapper objectMapper) {
        this.movimientoRepository = movimientoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public PaginaDTO<ReporteDTO> getReporteByClienteIdAndFecha(LocalDate fechaInicio, LocalDate fechaFin
            , Integer numeroPagina, Integer tamanioPagina)
    {
        numeroPagina = numeroPagina==null? 0 : numeroPagina;
        tamanioPagina = tamanioPagina==null? 10 : tamanioPagina;
        Pageable numeroPaginaRequest = PageRequest.of(numeroPagina,tamanioPagina);
        Page<IReporteProjection> paginaIReporte = movimientoRepository.getReporteByClienteIdAndFecha(fechaInicio, fechaFin,numeroPaginaRequest);
        List<ReporteDTO> listaMovimientosDTO = paginaIReporte.getContent().stream().map(this::mapToDto).toList();
        long cantidad  = paginaIReporte.getTotalElements();
        return new PaginaDTO<>(numeroPagina , tamanioPagina , cantidad, listaMovimientosDTO);
    }

    public ReporteDTO mapToDto(IReporteProjection reporteProjection) {
        ReporteDTO reporteDTO= new ReporteDTO(
                reporteProjection.getFecha(),
                reporteProjection.getNombresCliente(),
                reporteProjection.getNumeroCuenta(),
                reporteProjection.getTipoCuenta(),
                reporteProjection.getSaldoInicial(),
                reporteProjection.getEstado(),
                reporteProjection.getTipoMovimiento(),
                reporteProjection.getSaldoDisponible(),
                reporteProjection.getValor()
        );
        return objectMapper.convertValue(reporteProjection, ReporteDTO.class);
    }


}
