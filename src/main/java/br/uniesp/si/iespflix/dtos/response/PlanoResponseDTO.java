package br.uniesp.si.iespflix.dtos.response;

import java.util.UUID;

public record PlanoResponseDTO(
        UUID id,
        String codigo,
        Short limiteDiario,
        Short streamsSimultaneos
) {
}