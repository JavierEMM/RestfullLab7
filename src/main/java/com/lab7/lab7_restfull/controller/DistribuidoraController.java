package com.lab7.lab7_restfull.controller;


import com.lab7.lab7_restfull.entity.Distribuidora;
import com.lab7.lab7_restfull.repository.DistribuidorasRepository;
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
public class DistribuidoraController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @GetMapping(value="/api/distribuidora", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<Distribuidora> listaDistribuidoras() {
        List<Distribuidora> listaD = distribuidorasRepository.findAll();
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

    @PutMapping(value = "/api/distribuidora")
    public ResponseEntity<HashMap<String,Object>> actualizarDistribuidora(@RequestBody Distribuidora distribuidora) {

        HashMap<String, Object> responseJson = new HashMap<>();

        if (distribuidora.getId() != null && distribuidora.getId() > 0) {
            Optional<Distribuidora> opt = distribuidorasRepository.findById(distribuidora.getId());
            if (opt.isPresent()) {
                distribuidorasRepository.save(distribuidora);
                responseJson.put("estado", "actualizado");
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("estado", "error");
                responseJson.put("msg", "El producto a actualizar no existe");
                return ResponseEntity.badRequest().body(responseJson);
            }
        } else {
            responseJson.put("estado", "error");
            responseJson.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(responseJson);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un producto");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }



    @PutMapping(value = "/api/distribuidora/parcial")
    public ResponseEntity<HashMap<String, Object>> actualizarDistribuidoraParcial(@RequestBody Distribuidora distribuidora) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (distribuidora.getId() > 0) {
            Optional<Distribuidora> opt = distribuidorasRepository.findById(distribuidora.getId());
            if (opt.isPresent()) {
                Distribuidora distribuidoraFromDb = opt.get();

                if (distribuidora.getNombre() != null)
                    distribuidoraFromDb.setNombre(distribuidora.getNombre());

                if (distribuidora.getDescripcion() != null)
                    distribuidoraFromDb.setDescripcion(distribuidora.getDescripcion());

                if (distribuidora.getFundacion() != null)
                    distribuidoraFromDb.setFundacion(distribuidora.getFundacion());

                if (distribuidora.getWeb() != null)
                    distribuidoraFromDb.setWeb(distribuidora.getWeb());

                if (distribuidora.getIdsede() != null)
                    distribuidoraFromDb.setIdsede(distribuidora.getIdsede());

                distribuidorasRepository.save(distribuidoraFromDb);
                responseMap.put("estado", "actualizado");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("msg", "La distribuidora a actualizar no existe");
            }
        } else {
            responseMap.put("msg", "Debe enviar un ID");
        }
        responseMap.put("estado", "error");
        return ResponseEntity.badRequest().body(responseMap);
    }


    @DeleteMapping(value = "/api/distribuidora/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarDistribuidora(@PathVariable("id") String idStr) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            if (distribuidorasRepository.existsById(id)) {
                distribuidorasRepository.deleteById(id);
                responseMap.put("estado", "borrado exitoso");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "no se encontró el producto con id: " + id);
                return ResponseEntity.badRequest().body(responseMap);
            }
        } catch (NumberFormatException ex) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "El ID debe ser un número");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }



}
