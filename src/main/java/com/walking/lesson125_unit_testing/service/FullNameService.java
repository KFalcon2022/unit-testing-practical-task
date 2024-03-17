package com.walking.lesson125_unit_testing.service;

import com.walking.lesson125_unit_testing.model.FullName;
import com.walking.lesson125_unit_testing.parser.FullNameParser;
import com.walking.lesson125_unit_testing.validator.FullNameValidator;

public class FullNameService {
    private final FullNameParser parser;
    private final FullNameValidator validator;

    public FullNameService(FullNameParser parser, FullNameValidator validator) {
        this.parser = parser;
        this.validator = validator;
    }

    public FullName create(String nameString) {
        FullName fullName = parser.parse(nameString);
        validator.validate(fullName);

        return fullName;
    }
}
