package br.uniesp.si.iespflix.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ViaCepResponseDTO(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        @JsonProperty("erro") Boolean erro
) {
}