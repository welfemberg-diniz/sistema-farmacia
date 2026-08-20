package com.bergdiniz.sistemafarmacia.controller;

import com.bergdiniz.sistemafarmacia.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

@GetMapping
public ResponseEntity<?> buscarMedicamento(@RequestParam String nome, @RequestParam String codigoBarras, Pageable pageable){
    if (codigoBarras != null){
        return ResponseEntity.ok().body(service.buscarPorCodigoBarras(codigoBarras));
    }
    if (nome != null){
        return ResponseEntity.ok().body(service.buscarPorNome(nome));
    }
    return ResponseEntity.ok().body(service.listarTodos(pageable));
}

}
