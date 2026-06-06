package br.uniesp.si.iespflix.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumSubsetValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumSubset {
    String[] anyOf();
    String message() default "O valor fornecido não pertence ao domínio permitido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}