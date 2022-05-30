package com.aycap.kbb.batchdemo.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Person {

    @NotNull(message = "[last_name] must not be null")
    @NotEmpty(message = "[last_name] must not be empty")
    private String lastName;

    @NotNull(message = "[first_name] must not be null")
    @NotEmpty(message = "[first_name] must not be empty")
    private String firstName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName;
    }

}