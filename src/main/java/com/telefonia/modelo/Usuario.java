package com.telefonia.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String username;
    private String telefono;
    private String password;
    
    // Ubicación
    private String departamento;
    private String ciudad;
    
    // Dirección
    private String tipoDireccion;
    private String tipoVia;
    private String numeroVia;
    private String prefijoVia;
    private String cardinalidadVia;
    private String numeroViaCruce;
    private String prefijoViaCruce;
    private String cardinalidadViaCruce;
    private String numeroPlaca;
    private String unidadUrbanizacion;
    
    private LocalDateTime fechaRegistro;
    private Boolean activo;

    public Usuario() {
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    public Usuario(String nombre, String email, String username, String telefono, String password,
                   String departamento, String ciudad, String tipoDireccion, String tipoVia,
                   String numeroVia, String prefijoVia, String cardinalidadVia, String numeroViaCruce,
                   String prefijoViaCruce, String cardinalidadViaCruce, String numeroPlaca,
                   String unidadUrbanizacion) {
        this();
        this.nombre = nombre;
        this.email = email;
        this.username = username;
        this.telefono = telefono;
        this.password = password;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.tipoDireccion = tipoDireccion;
        this.tipoVia = tipoVia;
        this.numeroVia = numeroVia;
        this.prefijoVia = prefijoVia;
        this.cardinalidadVia = cardinalidadVia;
        this.numeroViaCruce = numeroViaCruce;
        this.prefijoViaCruce = prefijoViaCruce;
        this.cardinalidadViaCruce = cardinalidadViaCruce;
        this.numeroPlaca = numeroPlaca;
        this.unidadUrbanizacion = unidadUrbanizacion;
    }

    // Getters y Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNumeroVia() {
        return numeroVia;
    }

    public void setNumeroVia(String numeroVia) {
        this.numeroVia = numeroVia;
    }

    public String getPrefijoVia() {
        return prefijoVia;
    }

    public void setPrefijoVia(String prefijoVia) {
        this.prefijoVia = prefijoVia;
    }

    public String getCardinalidadVia() {
        return cardinalidadVia;
    }

    public void setCardinalidadVia(String cardinalidadVia) {
        this.cardinalidadVia = cardinalidadVia;
    }

    public String getNumeroViaCruce() {
        return numeroViaCruce;
    }

    public void setNumeroViaCruce(String numeroViaCruce) {
        this.numeroViaCruce = numeroViaCruce;
    }

    public String getPrefijoViaCruce() {
        return prefijoViaCruce;
    }

    public void setPrefijoViaCruce(String prefijoViaCruce) {
        this.prefijoViaCruce = prefijoViaCruce;
    }

    public String getCardinalidadViaCruce() {
        return cardinalidadViaCruce;
    }

    public void setCardinalidadViaCruce(String cardinalidadViaCruce) {
        this.cardinalidadViaCruce = cardinalidadViaCruce;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getUnidadUrbanizacion() {
        return unidadUrbanizacion;
    }

    public void setUnidadUrbanizacion(String unidadUrbanizacion) {
        this.unidadUrbanizacion = unidadUrbanizacion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
