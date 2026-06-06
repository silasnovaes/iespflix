package br.uniesp.si.iespflix.repositories;

import br.uniesp.si.iespflix.models.Favorito;
import br.uniesp.si.iespflix.models.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

    // RF#18 - Favoritos Recentes
    @Query("SELECT f FROM Favorito f WHERE f.usuario.id = :usuarioId ORDER BY f.criadoEm DESC")
    List<Favorito> findFavoritosRecentesByUsuarioId(@Param("usuarioId") UUID usuarioId);
}