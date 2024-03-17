package com.walking.lesson125_unit_testing.parser;

import com.walking.lesson125_unit_testing.exception.RegexValidationException;
import com.walking.lesson125_unit_testing.model.FullName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FullNameParserTest {

    private final FullNameParser fullNameParser = new FullNameParser();

    @ParameterizedTest
    @MethodSource("validNameStrings")
    void parse_success(String nameString, FullName expectedFullName) {
        FullName actual = fullNameParser.parse(nameString);

        assertEquals(expectedFullName, actual);
    }

    @ParameterizedTest
    @MethodSource("invalidNameStrings")
    void parse_regexValidation_failed(String nameString) {
        Executable executable = () -> fullNameParser.parse(nameString);

        assertThrows(RegexValidationException.class, executable);
    }

    @Test
    void parse_nullParameter_failed() {
        Executable executable = () -> fullNameParser.parse(null);

        assertThrows(NullPointerException.class, executable);
    }

    static Stream<Arguments> validNameStrings() {
        return Stream.of(
                Arguments.of("Иванов Иван Иванович", new FullName("Иван", "Иванов", "Иванович")),
                Arguments.of("Иванов-Иванов Иван Иванович", new FullName("Иван", "Иванов-Иванов", "Иванович")),
                Arguments.of("Иванов-Иванов И Иванович", new FullName("И", "Иванов-Иванов", "Иванович")),
                Arguments.of("И-Иванов И Иванович", new FullName("И", "И-Иванов", "Иванович")),
                Arguments.of("ИваНов Иван Иванович", new FullName("Иван", "ИваНов", "Иванович")),
                Arguments.of("И И Иванович", new FullName("И", "И", "Иванович")),
                Arguments.of("И-И И Иванович", new FullName("И", "И-И", "Иванович")),
                Arguments.of("И-Иванов-Ив И Иванович", new FullName("И", "И-Иванов-Ив", "Иванович")),
                Arguments.of("И-иванов И Иванович", new FullName("И", "И-иванов", "Иванович"))
        );
    }

    static Stream<Arguments> invalidNameStrings() {
        return Stream.of(
                Arguments.of("Иванов иван Иванович"),
                Arguments.of("И И И"),
                Arguments.of("И"),
                Arguments.of(""),
                Arguments.of("Ив1анов иван Иванович"),
                Arguments.of("иванов иван Иванович"),
                Arguments.of("Иванов Иван иванович"),
                Arguments.of("Ivanov Ivan")
        );
    }
}