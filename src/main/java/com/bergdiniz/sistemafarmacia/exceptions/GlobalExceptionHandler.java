package com.bergdiniz.sistemafarmacia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (MedicamentoNaoEncontrado.class)
    public ResponseEntity<String> naoEncontrado(MedicamentoNaoEncontrado ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler (MedicamentoJaCadastrado.class)
    public ResponseEntity<String> jaCadastrado(MedicamentoJaCadastrado ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler (EstoqueInsuficiente.class)
    public ResponseEntity<String> estoqueInsuficiente(EstoqueInsuficiente ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<String> erroValidarCadastro(MethodArgumentNotValidException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Todos os campos devem ser preenchidos: nome, concentracao, principioAtivo," +
                        " formaFarmaceutica, quantidadePorEmbalagem, fabricante, codigoBarras," +
                        " quantidadeEntrada");
    }

}
