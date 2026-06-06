package br.uniesp.si.iespflix.dtos.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// DTO de saída: Bloqueia estritamente a exposição do campo "senhaHash" e "cpfCnpj" na internet.
public record UsuarioResponseDTO(
        UUID id,
        String nomeCompleto,
        LocalDate dataNascimento,
        String email,
        String perfil,
        LocalDateTime criadoEm
) {
}