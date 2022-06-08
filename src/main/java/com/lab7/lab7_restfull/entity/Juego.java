package com.lab7.lab7_restfull.entity;

import javax.persistence.*;

@Entity
@Table(name = "juegos")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 448)
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "image", length = 400)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgenero")
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idplataforma")
    private Plataforma plataforma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ideditora")
    private Editora editora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddistribuidora")
    private Distribuidora distribuidora;

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero idgenero) {
        this.genero = idgenero;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma idplataforma) {
        this.plataforma = idplataforma;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora ideditora) {
        this.editora = ideditora;
    }

    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidora iddistribuidora) {
        this.distribuidora = iddistribuidora;
    }

}