package com.matheus.dias.projectzero.core.jpavalidator.cpf;

import com.matheus.dias.projectzero.core.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    private String value;


    public void initialize(Cpf constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        if (value.length() == 11) {
            return isValidCpf(value);
        }
        return false;
    }

    private boolean isValidCpf(String cpf) {
        String str = cpf;
        int size = 11;
        int resetIn = 2147483647;
        if (str != null && str.trim().length() == size) {
            boolean equals = true;
            char c = '9';

            int i;
            for (i = 0; i < str.length(); ++i) {
                if (i == 0) {
                    c = str.charAt(0);
                } else {
                    if (c != str.charAt(i)) {
                        equals = false;
                        break;
                    }

                    c = str.charAt(i);
                }
            }

            if (equals) {
                return false;
            } else {
                int sum = 0;
                int mul = 2;

                char car;
                for (i = size - 3; i >= 0; --i) {
                    car = str.charAt(i);
                    if (car < '0' || car > '9') {
                        return false;
                    }

                    sum += (car - 48) * mul;
                    ++mul;
                    if (mul > resetIn) {
                        mul = 2;
                    }
                }

                int rem = sum % 11;
                int dig1 = rem < 2 ? 0 : 11 - rem;
                sum = dig1 * 2;
                mul = 3;

                for (i = size - 3; i >= 0; --i) {
                    car = str.charAt(i);
                    sum += (car - 48) * mul;
                    ++mul;
                    if (mul > resetIn) {
                        mul = 2;
                    }
                }

                rem = sum % 11;
                int dig2 = rem < 2 ? 0 : 11 - rem;
                car = str.charAt(size - 2);
                if (car >= '0' && car <= '9') {
                    int dg1 = car - 48;
                    car = str.charAt(size - 1);
                    if (car >= '0' && car <= '9') {
                        int dg2 = car - 48;
                        return dig1 == dg1 && dig2 == dg2;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
