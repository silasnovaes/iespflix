package br.uniesp.si.iespflix.controllers;

import br.uniesp.si.iespflix.dtos.request.MetodoPagamentoRequestDTO;
import br.uniesp.si.iespflix.exceptions.ResourceNotFoundException;
import br.uniesp.si.iespflix.models.MetodoPagamento;
import br.uniesp.si.iespflix.models.Usuario;
import br.uniesp.si.iespflix.repositories.MetodoPagamentoRepository;
import br.uniesp.si.iespflix.repositories.UsuarioRepository;
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
@RequestMapping("/api/v1/metodos-pagamento")
@RequiredArgsConstructor
@Tag(name = "Métodos de Pagamento", description = "Endpoints para gerenciar cartões e tokens de pagamento")
public class MetodoPagamentoController {

    private final MetodoPagamentoRepository metodoPagamentoRepository;
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(summary = "Cadastrar um novo método de pagamento (tokenizado)")
    public ResponseEntity<MetodoPagamento> criar(@Valid @RequestBody MetodoPagamentoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        MetodoPagamento metodoPagamento = MetodoPagamento.builder()
                .usuario(usuario)
                .bandeira(requestDTO.bandeira())
                .ultimos4(requestDTO.ultimos4())
                .mesExp(requestDTO.mesExp())
                .anoExp(requestDTO.anoExp())
                .nomePortador(requestDTO.nomePortador())
                .tokenGateway(requestDTO.tokenGateway())
                .build();

        MetodoPagamento salvo = metodoPagamentoRepository.save(metodoPagamento);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @GetMapping("/{usuarioId}")
    @Operation(summary = "Consultar métodos de pagamento de um usuário")
    public ResponseEntity<List<MetodoPagamento>> listarPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(metodoPagamentoRepository.findByUsuarioId(usuarioId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um método de pagamento")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        MetodoPagamento metodo = metodoPagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Método de pagamento não encontrado."));
        metodoPagamentoRepository.delete(metodo);
        return ResponseEntity.noContent().build();
    }
}