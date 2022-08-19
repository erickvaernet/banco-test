package com.example.banco.model;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contrasenia;
    private Boolean estado;

}
