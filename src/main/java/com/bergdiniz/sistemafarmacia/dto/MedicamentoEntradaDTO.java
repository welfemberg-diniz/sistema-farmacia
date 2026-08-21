package com.bergdiniz.sistemafarmacia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoEntradaDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String concentracao;

    @NotBlank
    private String codigoBarras;
}
