package com.example.banco.model;

import javax.persistence.*;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer numeroCuenta;
    private String tipo;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    private String estado;
}
