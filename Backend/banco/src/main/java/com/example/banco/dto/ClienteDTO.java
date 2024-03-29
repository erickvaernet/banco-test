package com.example.banco.dto;

import com.example.banco.dto.validationinterface.CreateCliente;
import com.example.banco.dto.validationinterface.UpdateCliente;
import com.example.banco.exception.ClientIllegalArgumentException;
import com.example.banco.model.enums.GenerosEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


public class ClienteDTO {
    private Integer id;
    private String identificacion;
    private String nombres;
    private GenerosEnum genero;
    private LocalDate fechaNacimiento;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer edad;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private Boolean estado;


    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String identificacion, String nombres, GenerosEnum genero, LocalDate fechaNacimiento, Integer edad, String direccion, String telefono, String contrasenia, Boolean estado) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public ClienteDTO( String identificacion, String nombres, GenerosEnum genero, LocalDate fechaNacimiento, String direccion, String telefono, String contrasenia, Boolean estado) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    @Null(message = "No esta permitido ingresar un id" ,groups = {CreateCliente.class,UpdateCliente.class})
    public Integer getId() {
        return id;
    }

    @NotBlank(message = "La identificacion no puede ser nula o estar vacía" ,groups = {CreateCliente.class})
    @Length(min = 1, max = 255, message = "La identificacion debe tener entre 1 y 255 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    public String getIdentificacion() {
        return identificacion;
    }

    @NotBlank(message = "EL nombre no puede ser nulo o estar vacío" ,groups = {CreateCliente.class})
    @Length(min = 1, max = 255, message = "El Nombre debe tener entre 1 y 255 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    public String getNombres() {
        return nombres;
    }

    public GenerosEnum getGenero() {
        return genero;
    }

    @NotNull(message = "La fecha de nacimiento ser nula o estar vacía",groups = {CreateCliente.class})
    @Past(message = "La fecha de nacimiento no puede ser mayor a la fecha actual", groups = {CreateCliente.class, UpdateCliente.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @NotBlank(message = "La dirección no puede ser nula o estar vacía",groups = {CreateCliente.class})
    @Length(min = 1, max = 255, message = "El Nombre debe tener entre 1 y 255 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    public String getDireccion() {
        return direccion;
    }

    @NotBlank(message = "El telefono no puede ser nulo o estar vacío",groups = {CreateCliente.class})
    @Length(min = 3, max = 255, message = "El Telefono debe tener entre 3 y 255 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    public String getTelefono() {
        return telefono;
    }

    @NotNull(message = "El estado no puede ser nulo o estar vacío",groups = {CreateCliente.class})
    public Boolean getEstado() {
        return estado;
    }
    @NotBlank(message = "La contraseñia no puede ser nula o estar vacía",groups = {CreateCliente.class})
    @Length(min = 4, max = 255, message = "La contraseña debe tener entre 4 y 255 caracteres", groups = {CreateCliente.class, UpdateCliente.class})
    public String getContrasenia() {
        return contrasenia;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGenero(String genero) {
        if("MASCULINO".equalsIgnoreCase(genero) ||  "M".equalsIgnoreCase(genero)){
            this.genero = GenerosEnum.MASCULINO;
        }else if("FEMENINO".equalsIgnoreCase(genero) || "F".equalsIgnoreCase(genero)){
            this.genero = GenerosEnum.FEMENINO;
        }
        else if("OTRO".equalsIgnoreCase(genero) || "O".equalsIgnoreCase(genero)) {
            this.genero = GenerosEnum.OTRO;
        }
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        LocalDate fecha =LocalDate.parse(fechaNacimiento,
                DateTimeFormatter.ofPattern("uuuu-M-d")
                        .withResolverStyle(ResolverStyle.STRICT)
        );
        this.fechaNacimiento = fecha;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
