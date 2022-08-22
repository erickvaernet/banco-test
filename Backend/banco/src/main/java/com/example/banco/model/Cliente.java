package com.example.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "La contraseñia no puede ser nula o estar vacía")
    @Length(min = 4, max = 255, message = "La contraseña debe tener entre 4 y 255 caracteres")
    private String contrasenia;
    @NotNull(message = "El estado no puede ser nulo o estar vacío")
    private Boolean estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Cuenta> cuentas;

    public Cliente() {
        //No-args constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
