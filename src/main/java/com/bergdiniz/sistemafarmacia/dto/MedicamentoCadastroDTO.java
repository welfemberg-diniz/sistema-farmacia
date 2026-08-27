package com.bergdiniz.sistemafarmacia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MedicamentoCadastroDTO (

    @NotBlank String nome,

    @NotBlank String concentracao,

    @NotBlank String principioAtivo,

    @NotBlank String formaFarmaceutica,

   @NotBlank String quantidadePorEmbalagem,

    @NotBlank String fabricante,

    @NotBlank String codigoBarras,

    @NotNull @Positive Integer quantidadeEntrada

){}
