package com.lab7.lab7_restfull.controller;


import com.lab7.lab7_restfull.entity.Distribuidora;
import com.lab7.lab7_restfull.repository.DistribuidorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class DistribuidoraController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;


    @GetMapping(value="/distribuidora", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<Distribuidora> listaDistribuidoras(){
        List<Distribuidora> listaD=distribuidorasRepository.findAll();
        return listaD;
    }

    @GetMapping(value="/distribuidora/{id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
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


}
