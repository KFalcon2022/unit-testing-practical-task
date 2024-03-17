package com.walking.lesson125_unit_testing.validator;

import com.walking.lesson125_unit_testing.exception.RegexValidationException;
import com.walking.lesson125_unit_testing.model.FullName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FullNameValidatorTest {

    private final FullNameValidator fullNameValidator = new FullNameValidator();

    @ParameterizedTest
    @MethodSource("validFullNames")
    void validate_success(FullName fullName) {
        Executable executable = () -> fullNameValidator.validate(fullName);

        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @MethodSource("invalidFullNames")
    void validate_failed(FullName fullName) {
        Executable executable = () -> fullNameValidator.validate(fullName);

        assertThrows(RegexValidationException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("validSurnames")
    void validateSurname_success(String surname) {
        Executable executable = () -> fullNameValidator.validateSurname(surname);

        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @MethodSource("invalidSurnames")
    void validateSurname_failed(String surname) {
        Executable executable = () -> fullNameValidator.validateSurname(surname);

        assertThrows(RegexValidationException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("validNames")
    void validateName_success(String name) {
        Executable executable = () -> fullNameValidator.validateName(name);

        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @MethodSource("invalidNames")
    void validateName_failed(String name) {
        Executable executable = () -> fullNameValidator.validateName(name);

        assertThrows(RegexValidationException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("validPatronymics")
    void validatePatronymic_success(String patronymic) {
        Executable executable = () -> fullNameValidator.validateName(patronymic);

        assertDoesNotThrow(executable);
    }

    @ParameterizedTest
    @MethodSource("invalidPatronymics")
    void validatePatronymic_failed(String patronymic) {
        Executable executable = () -> fullNameValidator.validatePatronymic(patronymic);

        assertThrows(RegexValidationException.class, executable);
    }

    static Stream<Arguments> validFullNames() {
        return Stream.of(
                Arguments.of(new FullName("Иван", "Иванов", "Иванович")),
                Arguments.of(new FullName("Иван", "Иванов-Иванов", "Иванович")),
                Arguments.of(new FullName("И", "Иванов-Иванов", "Иванович")),
                Arguments.of(new FullName("И", "И-Иванов", "Иванович")),
                Arguments.of(new FullName("И", "И", "Иванович")),
                Arguments.of(new FullName("И", "И-И", "Иванович"))
        );
    }

    static Stream<Arguments> invalidFullNames() {
        return Stream.of(
                Arguments.of(new FullName("иван", "Иванов", "Иванович")),
                Arguments.of(new FullName("Иван Иванович", "Иванов", "")),
                Arguments.of(new FullName("Иван", "ИваНов", "Иванович")),
                Arguments.of(new FullName("И", "И", "И")),
                Arguments.of(new FullName("", "И", "Ив")),
                Arguments.of(new FullName("", "", "")),
                Arguments.of(new FullName("иван", "Ив1анов" , "Иванович")),
                Arguments.of(new FullName("иван", "иванов", "Иванович")),
                Arguments.of(new FullName("Иван", "Иванов", "иванович")),
                Arguments.of(new FullName( "И",  "И-иванов","Иванович")),
                Arguments.of(new FullName("Ivan", "Ivanov", ""))
        );
    }

    static Stream<Arguments> validSurnames() {
        return Stream.of(
                Arguments.of("Иванов"),
                Arguments.of("Иванов-Иванов"),
                Arguments.of("Иванов-Иванов"),
                Arguments.of("И-Иванов"),
                Arguments.of("И"),
                Arguments.of("И-И")
        );
    }

    static Stream<Arguments> invalidSurnames() {
        return Stream.of(
                Arguments.of("ИваНов"),
                Arguments.of(""),
                Arguments.of("Ив1анов"),
                Arguments.of("иванов"),
                Arguments.of("И-иванов"),
                Arguments.of("Ivanov"),
                Arguments.of("И-Иванов-Ив")
        );
    }

    static Stream<Arguments> validNames() {
        return Stream.of(
                Arguments.of("Иван"),
                Arguments.of("И")
        );
    }

    static Stream<Arguments> invalidNames() {
        return Stream.of(
                Arguments.of("ИваН"),
                Arguments.of(""),
                Arguments.of("Ив1ан"),
                Arguments.of("иван"),
                Arguments.of("И-иван"),
                Arguments.of("Ivan")
        );
    }

    static Stream<Arguments> validPatronymics() {
        return Stream.of(
                Arguments.of("Иванович")
        );
    }

    static Stream<Arguments> invalidPatronymics() {
        return Stream.of(
                Arguments.of("ИваНович"),
                Arguments.of(""),
                Arguments.of("Ив1анович"),
                Arguments.of("иванович"),
                Arguments.of("И-иванович"),
                Arguments.of("Ivanovich")
        );
    }
}