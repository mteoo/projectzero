package com.matheus.dias.projectzero.core.jpavalidator.email;

import com.matheus.dias.projectzero.core.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 320;
    private static final Pattern PATTERN = Pattern.compile("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))((@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-zA-Z0-9]{2,}(?:\\.[a-z]{2})?)$)|(@\\[?(25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.)((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$))");

    @Override
    public void initialize(Email constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        return value.length() >= MIN_LENGTH && value.length() <= MAX_LENGTH &&
                PATTERN.matcher(value).matches() && !value.contains("..");
    }
}
