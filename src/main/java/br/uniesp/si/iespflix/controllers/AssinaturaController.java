package br.uniesp.si.iespflix.controllers;

import br.uniesp.si.iespflix.dtos.request.AssinaturaRequestDTO;
import br.uniesp.si.iespflix.models.Assinatura;
import br.uniesp.si.iespflix.services.AssinaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assinaturas")
@RequiredArgsConstructor
@Tag(name = "Assinaturas", description = "Endpoints para controle de planos ativos dos usuários")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @PostMapping
    @Operation(summary = "Criar nova assinatura para um usuário")
    public ResponseEntity<Assinatura> criar(@Valid @RequestBody AssinaturaRequestDTO requestDTO) {
        Assinatura response = assinaturaService.criar(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar uma assinatura ativa")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        assinaturaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}