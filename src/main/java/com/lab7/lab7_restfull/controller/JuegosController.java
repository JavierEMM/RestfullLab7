package com.lab7.lab7_restfull.controller;

import com.lab7.lab7_restfull.entity.Juego;
import com.lab7.lab7_restfull.repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Controller
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @ResponseBody
    @GetMapping(value = "/api/juegos")
    public List<Juego> listarJuegosApi(){
        List<Juego> lista = juegosRepository.findAll();
        return lista;
    }

    @ResponseBody
    @GetMapping(value = "/api/juegos/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerJuegoPorId(@PathVariable("id") String idStr){
        HashMap<String,Object> responseJson = new HashMap<>();
        try{
            int id = Integer.parseInt(idStr);
            Optional<Juego> optJuegos = juegosRepository.findById(id);
            if(optJuegos.isPresent()){
                responseJson.put("result","success");
                responseJson.put("juego",optJuegos.get());
                return ResponseEntity.ok(responseJson);
            }else{
                responseJson.put("msg","Juego no encontrado");
            }
        }catch (NumberFormatException e){
            responseJson.put("msg","El ID debe ser un número entero positivo");
        }
        responseJson.put("result","failure");
        return ResponseEntity.badRequest().body(responseJson);
    }



}
