package com.example.banco.repository;

import com.example.banco.model.Movimiento;
import com.example.banco.repository.projections.IReporteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;



@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {
    Page<Movimiento> findAll(Pageable pageable);

    @Query(
          value = "Select m.fecha as fecha, p.nombres as nombresCliente, cu.id as numeroCuenta, cu.tipo as tipoCuenta, m.saldo_inicial as saldoInicial, cu.estado as estado, m.valor as valor, m.saldo_disponible as saldoDisponible, m.tipo as tipoMovimiento from personas as p join clientes as cl on cl.id= p.id join cuentas cu on cu.cliente_id = cl.id join movimientos m on m.cuenta_id = cu.id where m.fecha between ?1 and ?2",
          nativeQuery = true,
          countQuery = "select count(id) from movimientos where movimientos.fecha between ?1 and ?2;"
    )
    Page<IReporteProjection> getReporteByClienteIdAndFecha( LocalDate fechaInicio, LocalDate fechaFin, Pageable page);

}
