package com.proyecto.constructora.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tipocliente")
public class TipoCliente {

    public TipoCliente() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoClienteId;

    private String nombreCliente;

    // Getters y Setters
    public Long getTipoClienteId() {
        return tipoClienteId;
    }

    public void setTipoClienteId(Long tipoClienteId) {
        this.tipoClienteId = tipoClienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}