package com.bergdiniz.sistemafarmacia.dto;

import jakarta.validation.constraints.NotBlank;

public record MedicamentoEntradaDTO (
        @NotBlank String nome,
        @NotBlank String concentracao,
        @NotBlank String codigoBarras
){}
