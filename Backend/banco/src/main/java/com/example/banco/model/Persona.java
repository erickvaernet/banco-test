package com.example.banco.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "El nombre no puede ser nulo o estar vacío")
    private String nombre;
    @NotBlank(message = "El genero no puede ser nulo o estar vacío")
    private String genero;
    @NotBlank(message = "La edad no puede ser nula o estar vacía")
    private Integer edad;
    @NotBlank(message = "La dirección no puede ser nula o estar vacía")
    private String direccion;
    private String telefono;

}
