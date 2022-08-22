package com.example.banco.dto;

import com.example.banco.dto.validationinterface.CreateMovimiento;
import com.example.banco.dto.validationinterface.UpdateMovimiento;
import com.example.banco.model.Cuenta;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

public class MovimientoDTO {
    private Integer id;
    private LocalDate fecha;
    private String tipo;
    private Double valor;
    private Double saldo;
    private Cuenta cuenta;

    public MovimientoDTO(Integer id, LocalDate fecha, String tipo, Double valor, Double saldo) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    @Null(message = "No esta permitido ingresar un id" ,groups = {CreateMovimiento.class, UpdateMovimiento.class})
    public Integer getId() {
        return id;
    }
    @NotNull(message = "La fecha no puede ser nula", groups = {CreateMovimiento.class})
    public LocalDate getFecha() {
        return fecha;
    }
    @NotBlank(message = "El tipo de movimiento no puede ser nulo o estar vacío", groups = {CreateMovimiento.class})
    @Length(min = 6, max = 255, message = "EL tipo de movimiento debe tener entre 6 y 255 caracteres", groups = {CreateMovimiento.class, UpdateMovimiento.class})
    public String getTipo() {
        return tipo;
    }
    @NotNull(message = "El valor no puede ser nulo ", groups = {CreateMovimiento.class})
    public Double getValor() {
        return valor;
    }
    @NotNull(message = "El saldo no puede ser nulo", groups = {CreateMovimiento.class})
    public Double getSaldo() {
        return saldo;
    }

    @NotNull(message = "La cuenta no puede ser nula",groups = {CreateMovimiento.class})
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
