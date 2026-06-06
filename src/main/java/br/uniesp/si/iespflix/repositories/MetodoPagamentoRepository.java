package br.uniesp.si.iespflix.repositories;

import br.uniesp.si.iespflix.models.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, UUID> {
    List<MetodoPagamento> findByUsuarioId(UUID usuarioId);
}