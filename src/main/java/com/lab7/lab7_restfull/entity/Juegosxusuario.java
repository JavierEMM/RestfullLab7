package com.lab7.lab7_restfull.entity;

import javax.persistence.*;

@Entity
@Table(name = "juegosxusuario")
public class Juegosxusuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuegosxusuario", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjuego", nullable = false)
    private Juego idjuego;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario idusuario;

    @Column(name = "cantidad")
    private Integer cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Juego getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(Juego idjuego) {
        this.idjuego = idjuego;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}