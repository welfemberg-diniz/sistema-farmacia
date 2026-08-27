package com.bergdiniz.sistemafarmacia.controller;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoCadastroDTO;
import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import com.bergdiniz.sistemafarmacia.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

@GetMapping
public ResponseEntity<?> buscarMedicamento(
        @RequestParam(required = false) String nome,
        @RequestBody(required = false) String codigoBarras,
        Pageable pageable){

    if (codigoBarras != null){
        return ResponseEntity.ok(service.buscarPorCodigoBarras(codigoBarras));
    }
    if (nome != null){
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }
    return ResponseEntity.ok(service.listarTodos(pageable));
}

@PostMapping
    public ResponseEntity<?> cadastrarNovoMedicamento(@RequestBody @Valid MedicamentoCadastroDTO dto){
    service.cadastrarNovoMedicamento(dto);
    return ResponseEntity.ok().build();
}

@PatchMapping
    public ResponseEntity<?> atualizarMedicamento(@RequestBody String codigoBarras, Medicamento dadosNovos){
    return ResponseEntity.ok().body(service.atualizarMedicamento(codigoBarras, dadosNovos));
}

@DeleteMapping
    public ResponseEntity<?> deletarMedicamento(@RequestParam String codigoBarras){
    service.deletarMedicamento(codigoBarras);
    return ResponseEntity.ok().build();
}
}
