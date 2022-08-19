package com.example.banco.models;

import javax.persistence.*;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    private String tipo;
    private Double saldoInicial;
    private String estado;
}
