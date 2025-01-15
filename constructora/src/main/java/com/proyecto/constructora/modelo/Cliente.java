package com.proyecto.constructora.modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clientes")
public class Cliente {

    public Cliente() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    private String nombres;
    private String apellidos;
    private String cedula;
    private String correoElectronico;
    private String numeroCelular;
    private String direccionCliente;

    @ManyToOne
    @JoinColumn(name = "ciudadId")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "departamentoId")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "tipoClienteId")
    private TipoCliente tipoCliente;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistroSistema;

    private String estadoCliente;

    // Getters y Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Date getFechaRegistroSistema() {
        return fechaRegistroSistema;
    }

    public void setFechaRegistroSistema(Date fechaRegistroSistema) {
        this.fechaRegistroSistema = fechaRegistroSistema;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }
}
