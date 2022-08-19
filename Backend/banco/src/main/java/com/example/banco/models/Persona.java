package com.example.banco.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;

}
