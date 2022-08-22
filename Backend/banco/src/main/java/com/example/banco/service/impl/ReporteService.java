package com.example.banco.service.impl;


public class ReporteService  {

    /*
    private final IReporteRepository reporteRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public ReporteService(IReporteRepository reporteRepository, ObjectMapper objectMapper) {
        this.reporteRepository = reporteRepository;
        this.objectMapper = objectMapper;
    }



    @Override
    public List<ReporteDTO> getReporteByClienteIdAndFecha(Integer clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        List<IReporteProjection> listReportes = reporteRepository.getReporteByClienteIdAndFecha(clienteId,fechaInicio, fechaFin);
        return listReportes.stream().map(this::mapToDto).collect(java.util.stream.Collectors.toList());
    }

    public ReporteDTO mapToDto(IReporteProjection reporteProjection) {
        return objectMapper.convertValue(reporteProjection, ReporteDTO.class);
    }
     */

}
