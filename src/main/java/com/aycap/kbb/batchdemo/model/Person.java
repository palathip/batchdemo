package com.aycap.kbb.batchdemo.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Person {

    private String applicationNo;

    @NotNull(message = "[last_name] must not be null")
    @NotEmpty(message = "[last_name] must not be empty")
    private String lastName;

    @NotNull(message = "[first_name] must not be null")
    @NotEmpty(message = "[first_name] must not be empty")
    private String firstName;

    public Person(){

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