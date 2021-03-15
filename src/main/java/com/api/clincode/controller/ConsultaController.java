package com.api.clincode.controller;

import java.util.List;


import com.api.clincode.entity.ConsultaEntity;
import com.api.clincode.service.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaService service;

    @GetMapping()
    public List<ConsultaEntity> getAllConsultas() {
        return service.getAllConsultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaEntity> getConsultaByID(@PathVariable final int id) {
        ConsultaEntity entity = service.getConsultaByID(id);

        if(entity == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaEntity> putConsultaByID(@PathVariable final int id, @RequestBody ConsultaEntity entity) {
        ConsultaEntity consultaEntity = service.getConsultaByID(id);

        if(consultaEntity == null)
            return ResponseEntity.notFound().build();
        
        consultaEntity = service.alteraInformacoesByEntidade(consultaEntity, entity);
        
        return ResponseEntity.ok(consultaEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultaById(@PathVariable final int id) {
        if(service.deleteConsultaByID(id) == false)
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
