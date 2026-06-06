package br.uniesp.si.iespflix.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpjValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // A validação de obrigatoriedade deve ser feita via @NotBlank
        }

        String documento = value.replaceAll("\\D", "");

        if (documento.length() == 11) {
            return isCpfValido(documento);
        } else if (documento.length() == 14) {
            return isCnpjValido(documento);
        }
        return false;
    }

    private boolean isCpfValido(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0, peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }
            int r1 = 11 - (soma % 11);
            if (r1 > 9) r1 = 0;

            soma = 0; peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }
            int r2 = 11 - (soma % 11);
            if (r2 > 9) r2 = 0;

            return (cpf.charAt(9) - '0' == r1) && (cpf.charAt(10) - '0' == r2);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCnpjValido(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int soma = 0, peso = 2;
            for (int i = 11; i >= 0; i--) {
                soma += (cnpj.charAt(i) - '0') * peso;
                peso = (peso == 9) ? 2 : peso + 1;
            }
            int r1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

            soma = 0; peso = 2;
            for (int i = 12; i >= 0; i--) {
                soma += (cnpj.charAt(i) - '0') * peso;
                peso = (peso == 9) ? 2 : peso + 1;
            }
            int r2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

            return (cnpj.charAt(12) - '0' == r1) && (cnpj.charAt(13) - '0' == r2);
        } catch (Exception e) {
            return false;
        }
    }
}