package com.walking.lesson125_unit_testing.service;

import com.walking.lesson125_unit_testing.exception.RegexValidationException;
import com.walking.lesson125_unit_testing.model.FullName;
import com.walking.lesson125_unit_testing.parser.FullNameParser;
import com.walking.lesson125_unit_testing.validator.FullNameValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FullNameServiceTest {
    @Mock
    private FullNameParser parser;

    @Mock
    private FullNameValidator validator;

    @InjectMocks
    private FullNameService fullNameService;

    @Test
    void create_success() {
//        given
        var nameString = "Иванов Иван Иванович";
        var fullName = new FullName("Иван", "Иванов", "Иванович");

        doReturn(fullName).when(parser).parse(nameString);

//        when
        var actual = fullNameService.create(nameString);

//        then
        assertEquals(fullName, actual);

        verify(parser).parse(nameString);
        verify(validator).validate(fullName);

    }

    @Test
    void create_parserException_failed() {
//        given
        var nameString = "Ива1нов Иван Иванович";

        doThrow(RegexValidationException.class).when(parser).parse(nameString);

//        when
        Executable executable = () -> fullNameService.create(nameString);

//        then
        assertThrows(RegexValidationException.class, executable);

        verify(parser).parse(nameString);
        verify(validator, never()).validate(any());
    }

    @Test
    void create_validatorException_failed() {
//        given
        var nameString = "Иванов-ИВанов Иван Иванович";
        var fullName = new FullName("Иван", "Иванов-ИВанов", "Иванович");

        doReturn(fullName).when(parser).parse(nameString);
        doThrow(RegexValidationException.class).when(validator).validate(fullName);

//        when
        Executable executable = () -> fullNameService.create(nameString);

//        then
        assertThrows(RegexValidationException.class, executable);

        verify(parser).parse(nameString);
        verify(validator).validate(any());
    }
}