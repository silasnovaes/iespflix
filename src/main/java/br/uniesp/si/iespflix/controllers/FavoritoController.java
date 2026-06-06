package br.uniesp.si.iespflix.controllers;

import br.uniesp.si.iespflix.models.Favorito;
import br.uniesp.si.iespflix.repositories.FavoritoRepository;
import br.uniesp.si.iespflix.services.FavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favoritos")
@RequiredArgsConstructor
@Tag(name = "Favoritos", description = "Endpoints para gerenciamento da lista de favoritos do usuário")
public class FavoritoController {

    private final FavoritoRepository favoritoRepository;
    private final FavoritoService favoritoService;

    @PostMapping("/{usuarioId}/filmes/{filmeId}")
    @Operation(summary = "Adicionar filme aos favoritos do usuário")
    public ResponseEntity<Void> adicionar(@PathVariable UUID usuarioId, @PathVariable UUID filmeId) {
        favoritoService.adicionar(usuarioId, filmeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{usuarioId}/filmes/{filmeId}")
    @Operation(summary = "Remover filme dos favoritos do usuário")
    public ResponseEntity<Void> remover(@PathVariable UUID usuarioId, @PathVariable UUID filmeId) {
        favoritoService.remover(usuarioId, filmeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}")
    @Operation(summary = "Listar favoritos de um usuário específico")
    public ResponseEntity<List<Favorito>> listarPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(favoritoRepository.findFavoritosRecentesByUsuarioId(usuarioId));
    }
}