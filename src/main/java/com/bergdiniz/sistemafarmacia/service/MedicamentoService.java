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

    public Medicamento atualizarMedicamento(String nome, Medicamento dadosNovos){
        Medicamento medicamentoAtualizado = repository.findByName(nome).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));

        if (dadosNovos.getNome() != null){
            medicamentoAtualizado.setNome(dadosNovos.getNome());
        }
        if (dadosNovos.getCodigo_barras() != null){
            medicamentoAtualizado.setCodigo_barras(dadosNovos.getCodigo_barras());
        }
        if (dadosNovos.getConcentracao() != null){
            medicamentoAtualizado.setConcentracao(dadosNovos.getConcentracao());
        }
        if (dadosNovos.getFabricante() != null){
            medicamentoAtualizado.setFabricante(dadosNovos.getFabricante());
        }
        if (dadosNovos.getForma_farmaceutica() != null){
            medicamentoAtualizado.setForma_farmaceutica(dadosNovos.getForma_farmaceutica());
        }
        if (dadosNovos.getEstoque() != null){
            medicamentoAtualizado.setEstoque(dadosNovos.getEstoque());
        }
        if (dadosNovos.getQuantidade_medicamento() != null){
            medicamentoAtualizado.setQuantidade_medicamento(dadosNovos.getQuantidade_medicamento());
        }
        return repository.saveAndFlush(medicamentoAtualizado);
    }



}
