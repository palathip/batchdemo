package com.aycap.kbb.batchdemo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class Application {
    @NotEmpty(message = "[last_name] must not be empty")
    @Size(max = 20, message = "[application_no] must be no more than 20 characters")
    private String applicationNo;

    @NotEmpty(message = "[last_name] must not be empty")
    private String lastName;

    @NotEmpty(message = "[first_name] must not be empty")
    private String firstName;


}
