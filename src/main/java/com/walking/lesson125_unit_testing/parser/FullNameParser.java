package com.walking.lesson125_unit_testing.parser;

import com.walking.lesson125_unit_testing.exception.RegexValidationException;
import com.walking.lesson125_unit_testing.model.FullName;

public class FullNameParser {
    private static final String FULL_NAME_REGEX = "^[А-Я][А-Яа-я-]* [А-Я][а-я]* [А-Я][а-я]+$";
    private static final String SPLITERATOR = " ";

    public FullName parse(String nameString) {
        if (!nameString.matches(FULL_NAME_REGEX)) {
            throw new RegexValidationException(nameString, FULL_NAME_REGEX);
        }

        FullName fullName = new FullName();
        String[] splitNameString = nameString.split(SPLITERATOR);

        String surname = splitNameString[0];
        fullName.setSurname(surname);

        String name = splitNameString[1];
        fullName.setName(name);

        String patronymic = splitNameString[2];
        fullName.setPatronymic(patronymic);

        return fullName;
    }
}
