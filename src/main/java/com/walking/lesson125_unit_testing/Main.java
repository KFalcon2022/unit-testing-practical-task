package com.walking.lesson125_unit_testing;


import com.walking.lesson125_unit_testing.parser.FullNameParser;
import com.walking.lesson125_unit_testing.service.FullNameService;
import com.walking.lesson125_unit_testing.validator.FullNameValidator;

/**
 * На базе вашего решения
 * <a href="https://github.com/KFalcon2022/unit-testing-practical-task">задачи из урока 125</a>
 * актуализируйте юнит-тесты с использованием Mockito. Сложность задачи напрямую зависит от качества декомпозиции в
 * вашем предыдущем решении.
 */
public class Main {
    private static final FullNameValidator validator = new FullNameValidator();
    private static final FullNameParser parser = new FullNameParser();
    private static final FullNameService fullNameService = new FullNameService(parser, validator);

    public static void main(String[] args) {
        processFullName("Иванов Иван Иванович");
        processFullName("Ivanov Ivan");
    }

    private static void processFullName(String nameString) {
        System.out.println(fullNameService.create(nameString));
    }
}
