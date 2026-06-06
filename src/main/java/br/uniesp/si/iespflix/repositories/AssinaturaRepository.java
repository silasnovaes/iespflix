package br.uniesp.si.iespflix.repositories;

import br.uniesp.si.iespflix.models.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {

    // RF#19 - Assinaturas Ativas por Plano
    @Query("SELECT COUNT(a) FROM Assinatura a WHERE a.plano.id = :planoId AND a.status = 'ATIVA'")
    Long countAssinaturasAtivasByPlanoId(@Param("planoId") UUID planoId);
}