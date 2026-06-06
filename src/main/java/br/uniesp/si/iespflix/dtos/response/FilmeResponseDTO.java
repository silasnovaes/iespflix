package br.uniesp.si.iespflix.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

public record FilmeResponseDTO(
        UUID id,
        String titulo,
        String tipo,
        Short ano,
        Short duracaoMinutos,
        BigDecimal relevancia,
        String sinopse,
        String genero
) {
}