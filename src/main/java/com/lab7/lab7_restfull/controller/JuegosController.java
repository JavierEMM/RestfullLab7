package com.lab7.lab7_restfull.controller;

import com.lab7.lab7_restfull.entity.Juego;
import com.lab7.lab7_restfull.repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;



    @GetMapping(value = "/juegos")
    public List<Juego> listarJuegosApi(){
        List<Juego> lista = juegosRepository.findAll();
        return lista;
    }

    @GetMapping(value = "/juegos/{id}")
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


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExepcion(HttpServletRequest request){

        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseMap.put("estado","error");
            responseMap.put("msg","Debe enviar un juego");
        }
        return ResponseEntity.badRequest().body(responseMap);

    }


    @DeleteMapping(value = "/juegos/{id}")
    public ResponseEntity<HashMap<String,Object>> borrarJuego(@PathVariable("id") String idStr){
        HashMap<String,Object> responseMap = new HashMap<>();
        try{
            int id = Integer.parseInt(idStr);
            if(juegosRepository.existsById(id)){
                juegosRepository.deleteById(id);
                responseMap.put("estado","borrado exitoso");
                return ResponseEntity.ok(responseMap);
            }else{
                responseMap.put("estado","error");
                responseMap.put("msg", "no se encontró el producto con el id: "+id);
                return ResponseEntity.badRequest().body(responseMap);
            }
        }catch (NumberFormatException ex){
            responseMap.put("estado","error");
            responseMap.put("msg", "El id debe ser un número");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }
    @PutMapping(value = "/juegos")
    public ResponseEntity<HashMap<String,Object>> actualizarJuego(@RequestBody Juego juego){
        HashMap<String,Object> responseMap =  new HashMap<>();
        if(juego.getId() != null && juego.getId() > 0){
            Optional<Juego> juegoOptional = juegosRepository.findById(juego.getId());
            if(juegoOptional.isPresent()){
                juegosRepository.save(juego);
                responseMap.put("estado","actualizado");
                return ResponseEntity.ok(responseMap);
            }else{
                responseMap.put("estado","error");
                responseMap.put("msg","El juego a actualizar no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        }else{
            responseMap.put("estado","error");
            responseMap.put("msg","Debe pasar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }
    @PutMapping(value = "/juegos/parcial")
    public ResponseEntity<HashMap<String,Object>> actualizarJuegoParcial(@RequestBody Juego juego){
        HashMap<String,Object> responseMap =  new HashMap<>();
        if(juego.getId() > 0){
            Optional<Juego> juegoOptional = juegosRepository.findById(juego.getId());
            if(juegoOptional.isPresent()){
                Juego juego1 = juegoOptional.get();
                if(juego.getNombre() != null){
                    juego1.setNombre(juego.getNombre());
                }
                if(juego.getDescripcion() != null){
                    juego1.setDescripcion(juego.getDescripcion());
                }
                if(juego.getDistribuidora() != null){
                    juego1.setDistribuidora(juego.getDistribuidora());
                }
                if(juego.getEditora() != null){
                    juego1.setEditora(juego.getEditora());
                }
                if(juego.getGenero() != null){
                    juego1.setGenero(juego.getGenero());
                }
                if(juego.getPlataforma() != null){
                    juego1.setPlataforma(juego.getPlataforma());
                }
                if(juego.getPrecio() != null){
                    juego1.setPrecio(juego.getPrecio());
                }
                if(juego.getImage() != null){
                    juego1.setImage(juego.getImage());
                }
                juegosRepository.save(juego1);
                responseMap.put("estado","actualizado");
                return ResponseEntity.ok(responseMap);
            }else{
                responseMap.put("estado","error");
                responseMap.put("msg","El juego a actualizar no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        }else{
            responseMap.put("estado","error");
            responseMap.put("msg","Debe pasar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }
}
