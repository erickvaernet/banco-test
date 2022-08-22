package com.example.banco.dto;

import com.example.banco.dto.validationinterface.CreateCuenta;
import com.example.banco.model.Cliente;
import com.example.banco.model.enums.TipoCuentasEnum;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CuentaDTO {
   private Integer numeroCuenta;
    private TipoCuentasEnum tipo;
    private Double saldoInicial;
    private String estado;
    private Cliente cliente;

    public CuentaDTO() {
        //No-args constructor
    }
    @Null(message = "No esta permitido ingresar un id" ,groups = {CreateCuenta.class})
    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    @NotNull(message = "El saldo inicial no puede ser nulo o estar vacío",groups = {CreateCuenta.class})
    @DecimalMin(value = "0.00", message = "El saldo inical no puede ser menor que 0",groups = {CreateCuenta.class})
    public Double getSaldoInicial() {
        return saldoInicial;
    }

    @NotBlank(message = "El estado no puede ser nulo o estar vacío")
    public String getEstado() {
        return estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoCuentasEnum getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if("ahorro".equalsIgnoreCase(tipo)){
            this.tipo = TipoCuentasEnum.AHORRO;
        }else if("corriente".equalsIgnoreCase(tipo)){
            this.tipo = TipoCuentasEnum.CORRIENTE;
        }
        else {
            throw new IllegalArgumentException("El tipo de cuenta no es válido");
        }
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
