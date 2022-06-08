package com.lab7.lab7_restfull.entity;

import javax.persistence.*;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura", nullable = false)
    private Integer id;

    @Column(name = "fechaEnvio", nullable = false, length = 50)
    private String fechaEnvio;

    @Column(name = "tarjeta", nullable = false, length = 50)
    private String tarjeta;

    @Column(name = "codigoVerificacion", nullable = false, length = 5)
    private String codigoVerificacion;

    @Column(name = "direccion", nullable = false, length = 500)
    private String direccion;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idjuegosxusuario")
    private Juegosxusuario idjuegosxusuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Juegosxusuario getIdjuegosxusuario() {
        return idjuegosxusuario;
    }

    public void setIdjuegosxusuario(Juegosxusuario idjuegosxusuario) {
        this.idjuegosxusuario = idjuegosxusuario;
    }

}