package com.example.banco.model;

import com.example.banco.dto.validationinterface.CreateCliente;
import com.example.banco.dto.validationinterface.UpdateCliente;
import com.example.banco.model.enums.GenerosEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "personas")
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "EL nombre no puede ser nulo o estar vacío" ,groups = {CreateCliente.class})
    @Length(min = 1, max = 255, message = "El Nombre debe tener entre 1 y 250 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    private String nombres;
    @NotBlank(message = "La identificacion no puede ser nula o estar vacía")
    @Length(min = 1, max = 255, message = "La identificacion debe tener entre 1 y 250 caracteres")
    private String identificacion;
    @Enumerated(EnumType.STRING)
    @Column(name="genero")
    private GenerosEnum genero;
    @NotNull(message = "La fecha de nacimiento ser nula o estar vacía",groups = {CreateCliente.class})
    @Past(message = "La fecha de nacimiento no puede ser mayor a la fecha actual", groups = {CreateCliente.class, UpdateCliente.class})
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @NotBlank(message = "La dirección no puede ser nula o estar vacía",groups = {CreateCliente.class})
    @Length(min = 1, max = 255, message = "El Nombre debe tener entre 1 y 250 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    private String direccion;
    @NotBlank(message = "El telefono no puede ser nulo o estar vacío",groups = {CreateCliente.class})
    @Length(min = 3, max = 255, message = "El Telefono debe tener entre 3 y 250 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    private String telefono;

    public Persona() {
        //No-args constructor
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public GenerosEnum getGenero() {
        return genero;
    }

    public void setGenero(GenerosEnum genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
