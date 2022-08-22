package com.example.banco.model;

import com.example.banco.model.enums.TIpoMovimientoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TIpoMovimientoEnum tipo;
    @Column(name = "valor", nullable = false)
    private Double valor;
    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;
    @ManyToOne()
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
    @Column(name = "saldo_disponible", nullable = false)
    private Double saldoDisponible;


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

    public TIpoMovimientoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TIpoMovimientoEnum tipo) {
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

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
}
