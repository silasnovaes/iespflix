package br.uniesp.si.iespflix.controllers;

import br.uniesp.si.iespflix.dtos.request.FilmeRequestDTO;
import br.uniesp.si.iespflix.dtos.response.FilmeResponseDTO;
import br.uniesp.si.iespflix.services.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/filmes")
@RequiredArgsConstructor
@Tag(name = "Catálogo de Filmes", description = "Endpoints para consulta e administração de conteúdo")
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping
    @Operation(summary = "Listar todos os conteúdos")
    public ResponseEntity<List<FilmeResponseDTO>> listar() {
        return ResponseEntity.ok(filmeService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar um conteúdo por ID")
    public ResponseEntity<FilmeResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(filmeService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar um novo conteúdo (Requer Admin)")
    public ResponseEntity<FilmeResponseDTO> criar(@Valid @RequestBody FilmeRequestDTO requestDTO) {
        FilmeResponseDTO response = filmeService.criar(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um conteúdo existente (Requer Admin)")
    public ResponseEntity<FilmeResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody FilmeRequestDTO requestDTO) {
        return ResponseEntity.ok(filmeService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um conteúdo (Requer Admin)")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        filmeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}