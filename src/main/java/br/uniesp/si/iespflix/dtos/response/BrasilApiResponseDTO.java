package br.uniesp.si.iespflix.dtos.response;

import java.util.List;

public record BrasilApiResponseDTO(
        String state,
        List<String> cities
) {
}