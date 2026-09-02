package com.bergdiniz.sistemafarmacia.controller;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoEntradaDTO;
import com.bergdiniz.sistemafarmacia.dto.MedicamentoSaidaDTO;
import com.bergdiniz.sistemafarmacia.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamentos/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @PostMapping
    public ResponseEntity<?> entradaMedicamento(@RequestParam String codigoBarras, @RequestBody @Valid MedicamentoEntradaDTO dtoEntrada){
        service.entradaMedicamento(dtoEntrada, codigoBarras);
        return ResponseEntity.ok().body("Entrada realizada com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<?> saidaMedicamento(@RequestParam String codigoBarras, @RequestBody @Valid MedicamentoSaidaDTO dtoSaida){
        service.saidaMedicamento(dtoSaida, codigoBarras);
        return ResponseEntity.ok().body("Saída realizada com sucesso");
    }
}

