package com.walking.lesson125_unit_testing.model;

import java.util.Objects;

public class FullName {
    private String name;
    private String surname;
    private String patronymic;

    public FullName() {
    }

    public FullName(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FullName fullName = (FullName) o;
        return Objects.equals(name, fullName.name)
                && Objects.equals(surname, fullName.surname)
                && Objects.equals(patronymic, fullName.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic);
    }

    @Override
    public String toString() {
        return "%s %s %s".formatted(surname, name, patronymic);
    }
}
