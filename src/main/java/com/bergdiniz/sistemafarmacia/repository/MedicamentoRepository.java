package com.bergdiniz.sistemafarmacia.repository;

import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicamentoRepository extends JpaRepository <Medicamento, UUID> {


    Optional <Medicamento> findByCodigoBarras (String codigoBarras);
    boolean existsByCodigoBarras(String codigo_barras);
    List<Medicamento> findByNomeContainingIgnoreCase (String nome);
}
