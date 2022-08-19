package com.example.banco.repository;


import com.example.banco.repository.projections.IReporteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IReporteRepository {
    /*
    @Query()
    Page<IReporteProjection> findByFecha(Pageable pageable);
     */
}
