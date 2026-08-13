package com.bergdiniz.sistemafarmacia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
