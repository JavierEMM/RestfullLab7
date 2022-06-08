package com.lab7.lab7_restfull.controller;

import com.lab7.lab7_restfull.entity.Juego;
import com.lab7.lab7_restfull.repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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



    @GetMapping(value = "/api/juegos")
    public List<Juego> listarJuegosApi(){
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


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExepcion(HttpServletRequest request){

        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseMap.put("estado","error");
            responseMap.put("msg","Debe enviar un producto");
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

}
