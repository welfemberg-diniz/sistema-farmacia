package com.bergdiniz.sistemafarmacia.service;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoEntradaDTO;
import com.bergdiniz.sistemafarmacia.dto.MedicamentoSaidaDTO;
import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import com.bergdiniz.sistemafarmacia.exceptions.DadosIncorretos;
import com.bergdiniz.sistemafarmacia.exceptions.EstoqueInsuficiente;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoNaoCadastrado;
import com.bergdiniz.sistemafarmacia.exceptions.MedicamentoNaoEncontrado;
import com.bergdiniz.sistemafarmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private MedicamentoRepository repository;


    public void entradaMedicamento (MedicamentoEntradaDTO dtoEntrada, String codigoBarras) {

        Medicamento medicamento = repository.findByCodigoBarras (codigoBarras).orElseThrow(() -> new MedicamentoNaoCadastrado("Medicamento não cadastrado no sistema. Para alterar o estoque do produto é " +
                " preciso cadastra-lo primeiro"));

        if(!medicamento.getNome().equals(dtoEntrada.nome())){
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }
        if(!medicamento.getConcentracao().equals(dtoEntrada.concentracao())){
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }
        if(!medicamento.getQuantidadePorEmbalagem().equals(dtoEntrada.quantidadePorEmbalagem())) {
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }
        if(!medicamento.getFabricante().equals(dtoEntrada.fabricante())) {
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }

        medicamento.setEstoque(medicamento.getEstoque() + dtoEntrada.quantidadeEntrada());

        repository.save(medicamento);

    }


    public void saidaMedicamento(MedicamentoSaidaDTO dtoSaida, String codigoBarras) {

        Medicamento medicamento = repository.findByCodigoBarras (codigoBarras).orElseThrow(() -> new MedicamentoNaoCadastrado("Medicamento não cadastrado no sistema. Para alterar o estoque do produto é " +
                " preciso cadastra-lo primeiro"));

        if(!medicamento.getNome().equals(dtoSaida.nome())){
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }
        if(!medicamento.getConcentracao().equals(dtoSaida.concentracao())){
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }
        if(!medicamento.getQuantidadePorEmbalagem().equals(dtoSaida.quantidadePorEmbalagem())) {
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }
        if(!medicamento.getFabricante().equals(dtoSaida.fabricante())) {
            throw new DadosIncorretos("Os dados informados não correspondem aos dados do medicamento cadastrado no sistema. Verifique as informações e tente novamente.");
        }

        medicamento.setEstoque (medicamento.getEstoque() - dtoSaida.quantidadeSaida());

        repository.save(medicamento);
    }
}
