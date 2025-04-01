package ua.com.owu.productservice.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ua.com.owu.productservice.validation.validator.NotBlankIfPresentValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NotBlankIfPresentValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface NotBlankIfPresent {

    String message() default "{jakarta.validation.constraints.NotBlank.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}