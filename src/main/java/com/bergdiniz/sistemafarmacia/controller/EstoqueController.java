package com.bergdiniz.sistemafarmacia.controller;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoEntradaDTO;
import com.bergdiniz.sistemafarmacia.service.EstoqueService;
import com.bergdiniz.sistemafarmacia.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @PostMapping
    public ResponseEntity<?> entradaMedicamento(@RequestBody @Valid MedicamentoEntradaDTO dtoEntrada){
        service.entradaMedicamento(dtoEntrada);
        return ResponseEntity.ok().body("Entrada realizada com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<?> saidaMedicamento(@RequestBody String codigoBarras, int quantidade){
        service.saidaMedicamento(codigoBarras, quantidade);
        return ResponseEntity.ok().body("Saída realizada com sucesso");
    }
}

