package br.uniesp.si.iespflix.repositories;

import br.uniesp.si.iespflix.models.Filme;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, UUID> {

    // RF#14 - Conteúdo por Título
    @Query("SELECT f FROM Filme f ORDER BY f.titulo ASC")
    List<Filme> findAllOrderByTituloAsc();

    // RF#15 - Conteúdo por Gênero (Case-insensitive)
    @Query("SELECT f FROM Filme f WHERE LOWER(f.genero) = LOWER(:genero) ORDER BY f.titulo ASC")
    List<Filme> findByGeneroOrderByTituloAsc(@Param("genero") String genero);

    // RF#16 - Top N Conteúdos (Usando Pageable para o limitador N)
    @Query("SELECT f FROM Filme f ORDER BY f.relevancia DESC")
    List<Filme> findTopNByRelevancia(Pageable pageable);

    // RF#17 - Conteúdos Lançados Após Ano X
    @Query("SELECT f FROM Filme f WHERE f.ano > :ano")
    List<Filme> findByAnoGreaterThan(@Param("ano") Short ano);

    // RF#20 - Conteúdos com Imagem Principal (Utilizando 'sinopse' como fallback lógico de integridade,
    // já que o RF#02 não previu o atributo 'trailerUrl' na entidade)
    @Query("SELECT f FROM Filme f WHERE f.sinopse IS NOT NULL")
    List<Filme> findComImagemPrincipal();

    // RF#21 - Busca por Palavra-Chave no título ou sinopse
    @Query("SELECT f FROM Filme f WHERE LOWER(f.titulo) LIKE LOWER(CONCAT('%', :palavra, '%')) " +
            "OR LOWER(f.sinopse) LIKE LOWER(CONCAT('%', :palavra, '%')) ORDER BY f.relevancia DESC")
    List<Filme> searchByKeyword(@Param("palavra") String palavra);
}