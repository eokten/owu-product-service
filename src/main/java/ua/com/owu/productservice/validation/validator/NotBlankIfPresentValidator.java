package ua.com.owu.productservice.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.com.owu.productservice.validation.constraints.NotBlankIfPresent;

public class NotBlankIfPresentValidator implements ConstraintValidator<NotBlankIfPresent, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !value.isBlank();
    }
}