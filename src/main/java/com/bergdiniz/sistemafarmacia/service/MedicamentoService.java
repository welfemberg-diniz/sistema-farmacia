package com.bergdiniz.sistemafarmacia.service;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoCadastroDTO;
import com.bergdiniz.sistemafarmacia.dto.MedicamentoEntradaDTO;
import com.bergdiniz.sistemafarmacia.dto.MedicamentoResponseDTO;
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
                .nome(dto.nome())
                .formaFarmaceutica(dto.formaFarmaceutica())
                .concentracao(dto.concentracao())
                .quantidadePorEmbalagem(dto.quantidadePorEmbalagem())
                .principioAtivo(dto.principioAtivo())
                .fabricante(dto.fabricante())
                .codigoBarras(dto.codigoBarras())
                .estoque(dto.quantidadeEntrada())
                .build();

        if (repository.existsByCodigoBarras(medicamento.getCodigoBarras())){
            throw new MedicamentoJaCadastrado("Medicamento já cadastrado");
        }
        repository.save(medicamento);
    }


    public Medicamento buscarPorCodigoBarras(String codigoBarras){
        return repository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new MedicamentoNaoEncontrado("Medicamento não encontrado"));
    }

    public List<MedicamentoResponseDTO> buscarPorNome(String nome){
       List <MedicamentoResponseDTO> medicamentos = repository.findByNomeContainingIgnoreCase(nome);

       if (medicamentos.isEmpty()){
           throw new MedicamentoNaoEncontrado("Medicamento não encontrado");
       }
       return medicamentos;

    }

    public Page<MedicamentoResponseDTO> listarTodos(Pageable pageable){
        return repository.findTodos(pageable);
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




}
