package com.example.banco.model;

import com.example.banco.model.enums.TipoCuentasEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "cuentas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer numeroCuenta;
    @Enumerated(EnumType.STRING)
    private TipoCuentasEnum tipo;
    @Column(name = "saldo_inicial")
    @NotNull(message = "El saldo inicial no puede ser nulo o estar vacío")
    private Double saldo;
    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Movimiento> movimientos;

    public Cuenta() {
        //No-args constructor
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuentasEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuentasEnum tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
