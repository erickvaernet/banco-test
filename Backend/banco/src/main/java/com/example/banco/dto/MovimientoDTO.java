package com.example.banco.dto;

import java.time.LocalDate;

public class MovimientoDTO {
    private Integer id;
    private LocalDate fecha;
    private String tipo;
    private Double valor;
    private Double saldo;

    public MovimientoDTO(Integer id, LocalDate fecha, String tipo, Double valor, Double saldo) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
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
