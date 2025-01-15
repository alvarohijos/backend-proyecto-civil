package com.proyecto.constructora.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamento {

    public Departamento() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departamentoId;

    private String nombreDepartamento;

    // Getters y Setters
    public Long getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
}

