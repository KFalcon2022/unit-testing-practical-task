package com.walking.lesson125_unit_testing.validator;

import com.walking.lesson125_unit_testing.exception.RegexValidationException;
import com.walking.lesson125_unit_testing.model.FullName;

public class FullNameValidator {
    private static final String DOUBLE_SURNAME_REGEX = "[А-Я][а-я]*-[А-Я][а-я]*";
    private static final String NAME_REGEX = "[А-Я][а-я]*";
    private static final String PATRONYMIC_REGEX = "[А-Я][а-я]+";

    public void validate(FullName fullName) {
        validateSurname(fullName.getSurname());
        validateName(fullName.getName());
        validatePatronymic(fullName.getPatronymic());
    }

    public void validateSurname(String surname) {
        if (surname.contains("-")) {
            if (!surname.matches(DOUBLE_SURNAME_REGEX)) {
                throw new RegexValidationException(surname, DOUBLE_SURNAME_REGEX);
            }
        } else {
            validateName(surname);
        }
    }

    public void validateName(String name) {
        if (!name.matches(NAME_REGEX)) {
            throw new RegexValidationException(name, NAME_REGEX);
        }
    }

    public void validatePatronymic(String patronymic) {
        if (!patronymic.matches(PATRONYMIC_REGEX)) {
            throw new RegexValidationException(patronymic, PATRONYMIC_REGEX);
        }
    }
}
