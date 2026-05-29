package com.telefonia.modelo;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido; // Added this field
    private String cedula;
    private String telefono;
    private String direccion;
    private Long vendedorId;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellido, String cedula, String telefono, String direccion, Long vendedorId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido; // Added to constructor
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.vendedorId = vendedorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Added getter for apellido
    public String getApellido() {
        return apellido;
    }

    // Added setter for apellido
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }
}
