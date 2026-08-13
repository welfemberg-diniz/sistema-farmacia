package com.bergdiniz.sistemafarmacia.service;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoCadastroDTO;
import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoJaCadastrado;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoNaoEncontrado;
import com.bergdiniz.sistemafarmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    public void cadastrarMedicamento(MedicamentoCadastroDTO dto){

        Medicamento medicamento = Medicamento.builder()
                .nome(dto.getNome())
                .forma_farmaceutica(dto.getForma_farmaceutica())
                .concentracao(dto.getConcentracao())
                .quantidade_medicamento(dto.getQuantidade_medicamento())
                .principio_ativo(dto.getPrincipio_ativo())
                .fabricante(dto.getFabricante())
                .codigo_barras(dto.getCodigo_barras())
                .estoque(dto.getQuantidade_cadastrada())
                .build();

        if (repository.existsByCodigoBarras(medicamento.getCodigo_barras())){
            throw new MedicamentoJaCadastrado("Medicamento já cadastrado");
        }
        repository.save(medicamento);
    }

    public Medicamento buscarMedicamento(String nome){
       return repository.findByName(nome).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));
    }



}
