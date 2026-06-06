package br.uniesp.si.iespflix.dtos.request;

import br.uniesp.si.iespflix.validations.CpfCnpjValid;
import br.uniesp.si.iespflix.validations.EnumSubset;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UsuarioRequestDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        @Size(max = 150, message = "O nome não pode exceder 150 caracteres")
        String nomeCompleto,

        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        @Size(max = 254)
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
        String senha,

        @CpfCnpjValid(message = "O CPF ou CNPJ informado é matematicamente inválido")
        String cpfCnpj,

        @NotBlank(message = "O perfil é obrigatório")
        @EnumSubset(anyOf = {"USER", "ADMIN"}) // <- A ANOTAÇÃO É INSERIDA AQUI
        String perfil,

        String cep,

        String ddd
) {
}