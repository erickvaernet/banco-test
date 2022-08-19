package com.example.banco.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasenia;
    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;

}
