package com.lab7.lab7_restfull.controller;

import com.lab7.lab7_restfull.entity.Distribuidora;
import com.lab7.lab7_restfull.entity.Plataforma;
import com.lab7.lab7_restfull.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlataformaController {

    //Creamos este controller para comprobar un error del guardado de objetos pero aún así no funciona
    @Autowired
    PlataformaRepository plataformaRepository;

    @GetMapping(value = "/plataformas", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<Plataforma> listaPlataformas() {
        List<Plataforma> lista = plataformaRepository.findAll();
        return lista;
    }


}
