package com.example.banco.dto;

import com.example.banco.dto.validationinterface.*;
import com.example.banco.exception.ClientIllegalArgumentException;
import com.example.banco.model.Cliente;
import com.example.banco.model.enums.TipoCuentasEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CuentaDTO {
    private Integer numeroCuenta;
    private TipoCuentasEnum tipo;
    private Double saldo;
    private Boolean estado;
    private Cliente cliente;

    public CuentaDTO() {
        //No-args constructor
    }
    @Null(message = "No esta permitido ingresar un id" ,groups = {CreateCuenta.class, UpdateCuenta.class})
    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    @NotNull(message = "El saldo inicial no puede ser nulo o estar vacío",groups = {CreateCuenta.class})
    //@DecimalMin(value = "0.00", message = "El saldo inical no puede ser menor que 0",groups = {CreateCuenta.class})
    public Double getSaldo() {
        return saldo;
    }
    @NotNull(message = "El estado no puede ser nulo",groups = {CreateCuenta.class})
    public Boolean getEstado() {
        return estado;
    }
    @NotNull(message = "El cliente no puede ser nulo",groups = {CreateCuenta.class})
    public Cliente getCliente() {
        return cliente;
    }

    @NotNull(message = "El tipo de cuenta no puede ser nulo",groups = {CreateCuenta.class})
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
            throw new ClientIllegalArgumentException("El tipo de cuenta no es válido");
        }
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
