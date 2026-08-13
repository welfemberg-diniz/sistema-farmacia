package com.bergdiniz.sistemafarmacia.exceptions;

public class MedicamentoNaoEncontrado extends RuntimeException{

    public MedicamentoNaoEncontrado (String mensagem){
        super(mensagem);
    }
}
