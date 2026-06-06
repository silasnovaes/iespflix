package br.uniesp.si.iespflix.dtos.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record FilmeRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 200)
        String titulo,

        @NotBlank(message = "O tipo (FILME/SERIE) é obrigatório")
        String tipo,

        @NotNull(message = "O ano é obrigatório")
        @Min(value = 1888, message = "O ano não pode ser inferior a 1888")
        @Max(value = 2100, message = "O ano não pode ser superior a 2100")
        Short ano,

        @NotNull(message = "A duração é obrigatória")
        @Min(value = 1, message = "A duração mínima é de 1 minuto")
        @Max(value = 999, message = "A duração máxima é de 999 minutos")
        Short duracaoMinutos,

        @NotNull(message = "A relevância é obrigatória")
        @Digits(integer = 2, fraction = 2, message = "A relevância deve ter formato decimal (4,2)")
        BigDecimal relevancia,

        String sinopse,

        @Size(max = 50)
        String genero
) {
}