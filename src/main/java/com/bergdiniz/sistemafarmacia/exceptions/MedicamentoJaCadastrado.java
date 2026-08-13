package com.bergdiniz.sistemafarmacia.exceptions;

public class MedicamentoJaCadastrado extends RuntimeException{

    public MedicamentoJaCadastrado (String mensagem){
        super(mensagem);
    }
}
