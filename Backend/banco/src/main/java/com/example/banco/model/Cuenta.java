package com.example.banco.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer numeroCuenta;
    @NotBlank(message = "El tipo de cuenta no puede ser nulo o estar vacío")
    private String tipo;
    @Column(name = "saldo_inicial")
    @NotBlank(message = "El saldo inicial no puede ser nulo o estar vacío")
    private Double saldoInicial;
    private String estado;
}
