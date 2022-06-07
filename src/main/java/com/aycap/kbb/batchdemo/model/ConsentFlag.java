package com.aycap.kbb.batchdemo.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class ConsentFlag {

    @NotNull(message = "[consent_flag_101] must not be null")
    @Size(max = 1, message = "[consent_flag_101] must be no more than 1 characters")
    String flag101;

    @NotNull(message = "[consent_flag_102] must not be null")
    @Size(max = 1, message = "[consent_flag_102] must be no more than 1 characters")
    String flag102;

    @NotNull(message = "[consent_flag_103] must not be null")
    @Size(max = 1, message = "[consent_flag_103] must be no more than 1 characters")
    String flag103;

    @NotNull(message = "[consent_flag_104] must not be null")
    @Size(max = 1, message = "[consent_flag_104] must be no more than 1 characters")
    String flag104;

    @NotNull(message = "[consent_flag_105] must not be null")
    @Size(max = 1, message = "[consent_flag_105] must be no more than 1 characters")
    String flag105;

    @NotNull(message = "[consent_flag_106] must not be null")
    @Size(max = 1, message = "[consent_flag_106] must be no more than 1 characters")
    String flag106;

    @NotNull(message = "[consent_flag_107] must not be null")
    @Size(max = 1, message = "[consent_flag_107] must be no more than 1 characters")
    String flag107;
}
