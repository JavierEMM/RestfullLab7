package com.lab7.lab7_restfull.controller;

import com.lab7.lab7_restfull.entity.Genero;
import com.lab7.lab7_restfull.entity.Plataforma;
import com.lab7.lab7_restfull.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeneroController {

    @Autowired
    GeneroRepository generoRepository;


    @GetMapping(value = "/generos", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<Genero> listaGeneros() {
        List<Genero> lista = generoRepository.findAll();
        return lista;
    }
}
