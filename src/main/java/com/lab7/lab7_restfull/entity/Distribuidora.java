package com.lab7.lab7_restfull.entity;

import javax.persistence.*;

@Entity
@Table(name = "distribuidoras")
public class Distribuidora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddistribuidora", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "fundacion", nullable = false)
    private Integer fundacion;

    @Column(name = "web", length = 200)
    private String web;

    @ManyToOne
    @JoinColumn(name = "idsede")
    private Paises idsede;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFundacion() {
        return fundacion;
    }

    public void setFundacion(Integer fundacion) {
        this.fundacion = fundacion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Paises getIdsede() {
        return idsede;
    }

    public void setIdsede(Paises idsede) {
        this.idsede = idsede;
    }

}