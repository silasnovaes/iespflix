package br.uniesp.si.iespflix.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumSubsetValidator implements ConstraintValidator<EnumSubset, String> {

    private String[] allowedValues;

    @Override
    public void initialize(EnumSubset constraintAnnotation) {
        this.allowedValues = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return Arrays.asList(allowedValues).contains(value);
    }
}