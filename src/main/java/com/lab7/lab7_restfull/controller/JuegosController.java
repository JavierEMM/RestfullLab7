package com.lab7.lab7_restfull.controller;

import com.lab7.lab7_restfull.entity.Juego;
import com.lab7.lab7_restfull.repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;



    @GetMapping(value = "/api/juegos")
    public List<Juego> listarJuegosApi(){
        List<Juego> lista = juegosRepository.findAll();
        return lista;
    }

    @PostMapping(value = "/api/juegos")
    public List<Juego> listarJuegosApiPOST(){
        List<Juego> lista = juegosRepository.findAll();
        return lista;
    }

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

    @PostMapping(value = "/juegos")
    public ResponseEntity<HashMap<String,Object>> guardarJuego(@RequestBody Juego juego,
                                                                @RequestParam(value = "fetchId",required = false) boolean fetchId){

        HashMap<String,Object> responseMap = new HashMap<>();
        juegosRepository.save(juego);

        if(fetchId){
            responseMap.put("id",juego.getId());
        }
        responseMap.put("estado","creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }



}
