package com.example.banco.dto;

public class CuentaDTO {
    private Integer numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private String estado;

    public CuentaDTO(Integer numeroCuenta, String tipo, Double saldoInicial, String estado) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
