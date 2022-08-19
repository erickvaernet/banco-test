package com.example.banco.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private String tipo;
    private Double valor;
    private Double saldo;
}
