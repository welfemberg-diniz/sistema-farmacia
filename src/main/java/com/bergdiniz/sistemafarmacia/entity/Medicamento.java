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
    private String principio_ativo;

    @Column (name = "forma_farmaceutica")
    private String forma_farmaceutica;

    @Column (name = "quantidade_medicamento")
    private String quantidade_medicamento;

    @Column (name = "fabricante")
    private String fabricante;

    @Column (name = "codigo_barras")
    private String codigo_barras;

    @Column (name = "estoque")
    private Integer estoque;




}
