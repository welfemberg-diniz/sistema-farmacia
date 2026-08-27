package com.bergdiniz.sistemafarmacia.service;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoEntradaDTO;
import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import com.bergdiniz.sistemafarmacia.exceptions.EstoqueInsuficiente;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoNaoEncontrado;
import com.bergdiniz.sistemafarmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private MedicamentoRepository repository;


    public void entradaMedicamento(MedicamentoEntradaDTO dtoEntrada){
        Medicamento medicamento = Medicamento.builder()
                .nome(dtoEntrada.nome())
                .concentracao(dtoEntrada.concentracao())
                .codigoBarras(dtoEntrada.codigoBarras())
                .build();

        repository.save(medicamento);
    }


    public void saidaMedicamento(String codigoBarras, int quantidade){
        Medicamento medicamentoAlvo = repository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));

        if (quantidade > medicamentoAlvo.getEstoque()){
            throw new EstoqueInsuficiente("Estoque insuficiente");
        }
        medicamentoAlvo.setEstoque(medicamentoAlvo.getEstoque() - quantidade);

        repository.save(medicamentoAlvo);
    }
}
