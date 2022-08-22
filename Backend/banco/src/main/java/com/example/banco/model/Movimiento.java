package com.example.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "La fecha no puede ser nula o estar vacía")
    private LocalDate fecha;
    @NotBlank(message = "El tipo de movimiento no puede ser nulo o estar vacío")
    private String tipo;
    @NotNull(message = "El valor no puede ser nulo ")
    private Double valor;
    @NotNull(message = "El saldo no puede ser nulo")
    private Double saldo;
    @ManyToOne()
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;


    public Movimiento() {
        //No-args constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
