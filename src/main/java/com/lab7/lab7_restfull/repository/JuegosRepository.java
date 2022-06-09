package com.lab7.lab7_restfull.repository;


import com.lab7.lab7_restfull.entity.Juego;
import com.lab7.lab7_restfull.entity.JuegoUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuegosRepository extends JpaRepository<Juego,Integer> {
    @Query(value = "Select  j.idjuego, j.nombre, j.descripcion, g.nombre as genero, j.image as imageURL from gameshop4.juegos j " +
            "inner join gameshop4.juegosxusuario ju  on j.idjuego=ju.idjuego " +
            "inner join gameshop4.usuarios u on ju.idusuario=u.idusuario " +
            "inner join gameshop4.generos g on g.idgenero=j.idgenero Where u.idusuario= ?",nativeQuery = true)
    List<JuegoUserDTO> obtenerJuegosPorUser(int idusuario);


    @Query(value = "Select  j.* from gameshop4.juegos j " +
            "inner join gameshop4.juegosxusuario ju  on j.idjuego=ju.idjuego Where ju.idusuario= ?",nativeQuery = true)
    List<Juego> obtenerJuegosUsuario(int idusuario);


}
