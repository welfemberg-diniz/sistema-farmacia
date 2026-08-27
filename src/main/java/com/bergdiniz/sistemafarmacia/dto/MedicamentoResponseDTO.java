package com.bergdiniz.sistemafarmacia.dto;

public record MedicamentoResponseDTO(
        String nome,
        String concentracao,
        String formaFarmaceutica,
        String quantidadePorEmbalagem,
        String fabricante
) {}
