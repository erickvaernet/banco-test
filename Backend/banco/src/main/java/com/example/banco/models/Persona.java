package com.example.banco.models;

import javax.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;

}
