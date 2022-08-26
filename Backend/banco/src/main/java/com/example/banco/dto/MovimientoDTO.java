package com.example.banco.dto;

import com.example.banco.dto.validationinterface.CreateMovimiento;
import com.example.banco.dto.validationinterface.UpdateMovimiento;
import com.example.banco.exception.ClientIllegalArgumentException;
import com.example.banco.model.Cuenta;
import com.example.banco.model.enums.TIpoMovimientoEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

public class MovimientoDTO {
    private Integer id;
    private LocalDate fecha=LocalDate.now();
    private TIpoMovimientoEnum tipo;
    private Double valor;
    private Double saldoInicial;
    private Cuenta cuenta;
    private Double saldoDisponible;

    public MovimientoDTO() {
        //No-args constructor
    }

    public MovimientoDTO(Integer id, LocalDate fecha, TIpoMovimientoEnum tipo, Double valor, Double saldoInicial, Cuenta cuenta) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldoInicial = saldoInicial;
        this.cuenta = cuenta;
    }

    @Null(message = "No esta permitido ingresar un id" ,groups = {CreateMovimiento.class, UpdateMovimiento.class})
    public Integer getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @NotNull(message = "El tipo de movimiento no puede ser nulo", groups = {CreateMovimiento.class})
    public TIpoMovimientoEnum getTipo() {
        return tipo;
    }

    @NotNull(message = "El valor no puede ser nulo ", groups = {CreateMovimiento.class})
    public Double getValor() {
        return valor;
    }

    @Null(message = "El saldo inicial no se puede setear al crear el movimiento", groups = {CreateMovimiento.class})
    public Double getSaldoInicial() {
        return saldoInicial;
    }

    @NotNull(message = "La cuenta no puede ser nula",groups = {CreateMovimiento.class})
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = LocalDate.now();
    }

    public void setTipo(String tipo) {
        if("retiro".equalsIgnoreCase(tipo)){
            this.tipo = TIpoMovimientoEnum.RETIRO;
        }else if("deposito".equalsIgnoreCase(tipo)){
            this.tipo = TIpoMovimientoEnum.DEPOSITO;
        }
        else {
            throw new ClientIllegalArgumentException("El tipo de movimiento no es válido");
        }
    }
    @Null(message = "El saldo disponible no se puede setear al crear el movimiento", groups = {CreateMovimiento.class})
    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
