package br.uniesp.si.iespflix.dtos.request;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record MetodoPagamentoRequestDTO(
        @NotNull(message = "O ID do usuário é obrigatório")
        UUID usuarioId,

        @NotBlank(message = "A bandeira do cartão é obrigatória")
        String bandeira,

        @NotBlank(message = "Os últimos 4 dígitos são obrigatórios")
        @Size(min = 4, max = 4, message = "Deve conter exatamente 4 caracteres")
        String ultimos4,

        @NotNull(message = "O mês de expiração é obrigatório")
        @Min(value = 1, message = "Mês inválido")
        @Max(value = 12, message = "Mês inválido")
        Short mesExp,

        @NotNull(message = "O ano de expiração é obrigatório")
        Short anoExp,

        @NotBlank(message = "O nome do portador é obrigatório")
        @Size(max = 150)
        String nomePortador,

        @NotBlank(message = "O token do gateway de pagamento é obrigatório")
        String tokenGateway
) {
}