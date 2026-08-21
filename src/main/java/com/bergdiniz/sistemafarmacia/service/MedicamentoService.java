package com.bergdiniz.sistemafarmacia.service;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoCadastroDTO;
import com.bergdiniz.sistemafarmacia.dto.MedicamentoEntradaDTO;
import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import com.bergdiniz.sistemafarmacia.exceptions.EstoqueInsuficiente;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoJaCadastrado;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoNaoEncontrado;
import com.bergdiniz.sistemafarmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    public void cadastrarNovoMedicamento(MedicamentoCadastroDTO dto){

        Medicamento medicamento = Medicamento.builder()
                .nome(dto.getNome())
                .formaFarmaceutica(dto.getFormaFarmaceutica())
                .concentracao(dto.getConcentracao())
                .quantidadePorEmbalagem(dto.getQuantidadePorEmbalagem())
                .principioAtivo(dto.getPrincipioAtivo())
                .fabricante(dto.getFabricante())
                .codigoBarras(dto.getCodigoBarras())
                .estoque(dto.getQuantidadeEntrada())
                .build();

        if (repository.existsByCodigoBarras(medicamento.getCodigoBarras())){
            throw new MedicamentoJaCadastrado("Medicamento já cadastrado");
        }
        repository.save(medicamento);
    }

    public void entradaMedicamento(MedicamentoEntradaDTO dtoEntrada){
        Medicamento medicamento = Medicamento.builder()
                .nome(dtoEntrada.getNome())
                .concentracao(dtoEntrada.getConcentracao())
                .codigoBarras(dtoEntrada.getCodigoBarras())
                .build();

        repository.save(medicamento);
    }

    public Medicamento buscarPorCodigoBarras(String codigoBarras){
        return repository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));
    }

    public List<Medicamento> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Page<Medicamento> listarTodos(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Medicamento atualizarMedicamento(String codigoBarras, Medicamento dadosNovos){
        Medicamento medicamentoAtualizado = repository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));

        if (dadosNovos.getNome() != null){
            medicamentoAtualizado.setNome(dadosNovos.getNome());
        }
        if (dadosNovos.getCodigoBarras() != null){
            medicamentoAtualizado.setCodigoBarras(dadosNovos.getCodigoBarras());
        }
        if (dadosNovos.getConcentracao() != null){
            medicamentoAtualizado.setConcentracao(dadosNovos.getConcentracao());
        }
        if (dadosNovos.getFabricante() != null){
            medicamentoAtualizado.setFabricante(dadosNovos.getFabricante());
        }
        if (dadosNovos.getFormaFarmaceutica() != null){
            medicamentoAtualizado.setFormaFarmaceutica(dadosNovos.getFormaFarmaceutica());
        }
        if (dadosNovos.getEstoque() != null){
            medicamentoAtualizado.setEstoque(dadosNovos.getEstoque());
        }
        if (dadosNovos.getQuantidadePorEmbalagem() != null){
            medicamentoAtualizado.setQuantidadePorEmbalagem(dadosNovos.getQuantidadePorEmbalagem());
        }
        return repository.saveAndFlush(medicamentoAtualizado);
    }
    public void deletarMedicamento(String codigoBarras){
        Medicamento medicamentoAlvo = repository.findByCodigoBarras(codigoBarras).orElseThrow(()-> new MedicamentoNaoEncontrado("Medicamento não encontrado"));
        repository.delete(medicamentoAlvo);
    }
    public void saidaMedicamento(String codigoBarras, int quantidade){
        Medicamento medicamentoAlvo = repository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));

        if (quantidade > medicamentoAlvo.getEstoque()){
            throw new EstoqueInsuficiente ("Estoque insuficiente");
        }
        medicamentoAlvo.setEstoque(medicamentoAlvo.getEstoque() - quantidade);

        repository.save(medicamentoAlvo);
    }



}
