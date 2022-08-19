package com.example.banco.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "La fecha no puede ser nula o estar vacía")
    private LocalDate fecha;
    @NotBlank(message = "El tipo de movimiento no puede ser nulo o estar vacío")
    private String tipo;
    @NotBlank(message = "El valor no puede ser nulo o estar vacío")
    private Double valor;
    @NotBlank(message = "El saldo no puede ser nulo o estar vacío")
    private Double saldo;

    /*
    @ManyToOne()
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
    */


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
