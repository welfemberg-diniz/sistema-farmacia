package com.bergdiniz.sistemafarmacia.exceptions;

public class EstoqueInsuficiente extends RuntimeException{

    public EstoqueInsuficiente(String mensagem){
        super(mensagem);
    }
}
