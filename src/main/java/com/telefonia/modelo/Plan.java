package com.telefonia.modelo;

import jakarta.persistence.*;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio;
    private int gigas;
    private String descripcion;

    public Plan() {
    }

    public Plan(Long id, String nombre, double precio, int gigas, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.gigas = gigas;
        this.descripcion = descripcion;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getGigas() {
        return gigas;
    }

    public void setGigas(int gigas) {
        this.gigas = gigas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
