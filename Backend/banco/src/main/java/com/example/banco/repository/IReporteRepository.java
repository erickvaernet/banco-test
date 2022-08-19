package com.example.banco.repository;


import com.example.banco.repository.projections.IReporteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReporteRepository {
    @Query(value = "")
    List<IReporteProjection> getReporteByFecha(Integer page, Integer size, LocalDate fechaInicio,LocalDate fechaFin);

}
