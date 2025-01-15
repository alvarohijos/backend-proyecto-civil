package com.proyecto.constructora.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "ciudades")
public class Ciudad {
    public Ciudad() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ciudadId;

    private String nombreCiudad;


    @ManyToOne
    @JoinColumn(name = "departamentoId", nullable = false)
    private Departamento departamento;

    // Getters y Setters
    public Long getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}

