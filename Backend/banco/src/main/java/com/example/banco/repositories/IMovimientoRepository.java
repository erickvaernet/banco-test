package com.example.banco.repositories;

import com.example.banco.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {

}
