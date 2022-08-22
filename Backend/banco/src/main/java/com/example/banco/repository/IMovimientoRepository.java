package com.example.banco.repository;

import com.example.banco.model.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {
    Page<Movimiento> findAll(Pageable pageable);

    /*
    @Query(value = "Select m.fecha as fecha, c.nombre as nombre, cu.numero_cuenta as numeroCuenta, cu.tipo_cuenta as tipoCuenta, cu.saldo_inicial as saldoInicial, cu.estado as estado, m.valor as valor from cliente as c  join cuenta cu on cu.cliente_id = c.cliente_id join movimientos m on m.cuenta_id = cu.cuenta_id where c.cliente_id = ?1 and m.fecha between \"?2\" and \"?3\"")
    List<IReporteProjection> getReporteByClienteIdAndFecha(Integer clienteId, LocalDate fechaInicio, LocalDate fechaFin);
    */
}
