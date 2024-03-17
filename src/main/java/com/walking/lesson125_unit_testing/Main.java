package com.walking.lesson125_unit_testing;


import com.walking.lesson125_unit_testing.model.FullName;
import com.walking.lesson125_unit_testing.parser.FullNameParser;
import com.walking.lesson125_unit_testing.validator.FullNameValidator;

/**
 * На базе представленного решения
 * <a href="https://github.com/KFalcon2022/practical-tasks/blob/master/src/com/walking/lesson30_regex/task2/Main.java">задачи 2 урока 30</a>
 * произведите декомпозицию решения с учетом объектной модели и покройте получившееся решение юнит-тестами.
 */
public class Main {
    private static final FullNameValidator validator = new FullNameValidator();
    private static final FullNameParser parser = new FullNameParser();

    public static void main(String[] args) {
        processFullName("Иванов Иван Иванович");
        processFullName("Ivanov Ivan");
    }

    private static void processFullName(String nameString) {
        FullName fullName = parser.parse(nameString);
        validator.validate(fullName);

        System.out.println(fullName);
    }
}
