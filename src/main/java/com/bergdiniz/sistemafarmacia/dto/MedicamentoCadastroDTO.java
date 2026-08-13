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
    private String principio_ativo;

    @NotBlank
    private String forma_farmaceutica;

   @NotBlank
    private String quantidade_medicamento;

    @NotBlank
    private String fabricante;

    @NotBlank
    private String codigo_barras;

    @NotNull
    @Positive
    private Integer quantidade_cadastrada;
}
