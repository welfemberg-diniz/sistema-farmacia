package com.bergdiniz.sistemafarmacia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoCadastroDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String concentracao;

    @NotBlank
    private String principioAtivo;

    @NotBlank
    private String formaFarmaceutica;

   @NotBlank
    private String quantidadePorEmbalagem;

    @NotBlank
    private String fabricante;

    @NotBlank
    private String codigoBarras;

    @NotNull
    @Positive
    private Integer quantidadeEntrada;
}
