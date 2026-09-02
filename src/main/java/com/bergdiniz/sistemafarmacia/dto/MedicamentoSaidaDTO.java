package com.bergdiniz.sistemafarmacia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MedicamentoSaidaDTO (
        @NotBlank String nome,
        @NotBlank String concentracao,
        @NotBlank String quantidadePorEmbalagem,
        @NotBlank String fabricante,
        @NotNull @Positive Integer quantidadeSaida) {
}
