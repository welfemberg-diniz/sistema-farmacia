package com.bergdiniz.sistemafarmacia.repository;

import com.bergdiniz.sistemafarmacia.dto.MedicamentoResponseDTO;
import com.bergdiniz.sistemafarmacia.entity.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicamentoRepository extends JpaRepository <Medicamento, UUID> {


    Optional <Medicamento> findByCodigoBarras (String codigoBarras);
    boolean existsByCodigoBarras(String codigo_barras);
    List<MedicamentoResponseDTO> findByNomeContainingIgnoreCase (String nome);

    @Query("""
        SELECT new com.bergdiniz.sistemafarmacia.dto.MedicamentoResponseDTO(
        m.nome,
        m.concentracao,
        m.formaFarmaceutica,
        m.quantidadePorEmbalagem,
        m.fabricante,
        m.estoque
        )
        FROM Medicamento m

""")
    Page<MedicamentoResponseDTO> findTodos(Pageable pageable);
}
