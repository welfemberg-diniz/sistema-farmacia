package com.bergdiniz.sistemafarmacia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "medicamentos")
@Entity

public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "concentracao")
    private String concentracao;

    @Column (name = "principio_ativo")
    private String principioAtivo;

    @Column (name = "forma_farmaceutica")
    private String formaFarmaceutica;

    @Column (name = "quantidade_medicamento")
    private String quantidadePorEmbalagem;

    @Column (name = "fabricante")
    private String fabricante;

    @Column (name = "codigo_barras")
    private String codigoBarras;

    @Column (name = "estoque")
    private Integer estoque;




}
