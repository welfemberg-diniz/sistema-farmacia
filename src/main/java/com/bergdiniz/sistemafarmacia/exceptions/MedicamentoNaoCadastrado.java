package com.bergdiniz.sistemafarmacia.exceptions;

public class MedicamentoNaoCadastrado extends RuntimeException{

    public MedicamentoNaoCadastrado(String msg){
        super(msg);
    }
}
