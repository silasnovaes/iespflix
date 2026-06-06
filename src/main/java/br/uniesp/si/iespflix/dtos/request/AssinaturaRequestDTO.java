package br.uniesp.si.iespflix.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AssinaturaRequestDTO(
        @NotNull(message = "O ID do usuário é obrigatório")
        UUID usuarioId,

        @NotBlank(message = "O código do plano é obrigatório (ex: BASICO, PADRAO, PREMIUM)")
        String codigoPlano
) {
}