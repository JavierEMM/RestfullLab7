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
    private Genero idgenero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idplataforma")
    private Plataforma idplataforma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ideditora")
    private Editora ideditora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddistribuidora")
    private Distribuidora iddistribuidora;

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

    public Genero getIdgenero() {
        return idgenero;
    }

    public void setIdgenero(Genero idgenero) {
        this.idgenero = idgenero;
    }

    public Plataforma getIdplataforma() {
        return idplataforma;
    }

    public void setIdplataforma(Plataforma idplataforma) {
        this.idplataforma = idplataforma;
    }

    public Editora getIdeditora() {
        return ideditora;
    }

    public void setIdeditora(Editora ideditora) {
        this.ideditora = ideditora;
    }

    public Distribuidora getIddistribuidora() {
        return iddistribuidora;
    }

    public void setIddistribuidora(Distribuidora iddistribuidora) {
        this.iddistribuidora = iddistribuidora;
    }

}