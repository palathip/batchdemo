package com.aycap.kbb.batchdemo.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Person {
    @NotEmpty(message = "[last_name] must not be empty")
    @Size(max = 20, message = "[application_no] must be no more than 20 characters")
    private String applicationNo;
    @NotEmpty(message = "[last_name] must not be empty")
    private String lastName;

    @NotEmpty(message = "[first_name] must not be empty")
    private String firstName;

    public Person() {
    }
    public Person(String applicationNo,String firstName, String lastName) {
        this.applicationNo = applicationNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "applicationNo: " + applicationNo + ", firstName: " + firstName + ", lastName: " + lastName;
    }

}