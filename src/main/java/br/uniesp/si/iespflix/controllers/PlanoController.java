package br.uniesp.si.iespflix.controllers;

import br.uniesp.si.iespflix.dtos.response.PlanoResponseDTO;
import br.uniesp.si.iespflix.mappers.PlanoMapper;
import br.uniesp.si.iespflix.models.Plano;
import br.uniesp.si.iespflix.repositories.PlanoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/planos")
@RequiredArgsConstructor
@Tag(name = "Planos", description = "Endpoints para listar os planos disponíveis")
public class PlanoController {

    private final PlanoRepository planoRepository;
    private final PlanoMapper planoMapper;

    @GetMapping
    @Operation(summary = "Listar todos os planos")
    public ResponseEntity<List<PlanoResponseDTO>> listar() {
        List<PlanoResponseDTO> planos = planoRepository.findAll().stream()
                .map(planoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(planos);
    }
}