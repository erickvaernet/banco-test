package com.example.banco.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "La fecha no puede ser nula o estar vacía")
    private LocalDate fecha;
    @NotBlank(message = "El tipo de movimiento no puede ser nulo o estar vacío")
    private String tipo;
    @NotBlank(message = "El valor no puede ser nulo o estar vacío")
    private Double valor;
    @NotBlank(message = "El saldo no puede ser nulo o estar vacío")
    private Double saldo;
}
