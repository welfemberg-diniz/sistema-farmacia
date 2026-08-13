package com.bergdiniz.sistemafarmacia.repository;

import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicamentoRepository extends JpaRepository <Medicamento, UUID> {

    Optional <Medicamento> findByName(String nome);
    boolean existsByCodigoBarras(String codigo_barras);
}
