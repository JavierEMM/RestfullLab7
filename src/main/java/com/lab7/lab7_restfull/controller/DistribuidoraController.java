package com.lab7.lab7_restfull.controller;


import com.lab7.lab7_restfull.entity.Distribuidora;
import com.lab7.lab7_restfull.repository.DistribuidorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class DistribuidoraController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;


    @GetMapping(value="/api/distribuidora", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<Distribuidora> listaDistribuidoras(){
        List<Distribuidora> listaD=distribuidorasRepository.findAll();
        return listaD;
    }

    @GetMapping(value="/api/distribuidora/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public ResponseEntity<HashMap<String, Object>> obtenerDistribuidorasId(@PathVariable("id") String idstr){
        HashMap<String, Object> responseJson = new HashMap<>();


       try{
           int id = Integer.parseInt(idstr);
           Optional<Distribuidora> optionalDistribuidora = distribuidorasRepository.findById(id);
           if(optionalDistribuidora.isPresent()){
               responseJson.put("result","success");
               responseJson.put("product",optionalDistribuidora.get());
               return ResponseEntity.ok(responseJson);
           }else{
               responseJson.put("result","failure");
               responseJson.put("msg","La distribuidora con ese Id no existe");
               return ResponseEntity.ok(responseJson);
           }
       }catch(NumberFormatException e){
           responseJson.put("result","failure");
           responseJson.put("msg","El id debe ser un código numérico");
           return ResponseEntity.badRequest().body(responseJson);
       }
    }

    @PostMapping("/api/distribuidora")
    public ResponseEntity<HashMap<String, Object>> crearDistribuidora(
            @RequestBody Distribuidora distribuidora,
            @RequestParam(value = "fetchId", required = false) boolean fetchId){
        HashMap<String, Object> responseJson = new HashMap<>();
        HashMap<String, Object> responseMap = new HashMap<>();

        distribuidorasRepository.save(distribuidora);
        if(fetchId){
            responseMap.put("id",distribuidora.getId());
        }
        responseMap.put("creado","ok");
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, Object>> gestionarExcepcion(HttpServletRequest request){
        HashMap<String, Object> responseJson = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseJson.put("estado", "error");
            responseJson.put("msg","debes enviar el producto");
        }
        return  ResponseEntity.badRequest().body(responseJson);
    }


}
