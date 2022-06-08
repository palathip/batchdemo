package com.aycap.kbb.batchdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class ConsentFlag {

    @NotNull(message = "[consent_flag_101] must not be null")
    @Size(max = 1, message = "[consent_flag_101] must be no more than 1 characters")
    @JsonProperty("101")
    String flag101;

    @NotNull(message = "[consent_flag_102] must not be null")
    @Size(max = 1, message = "[consent_flag_102] must be no more than 1 characters")
    @JsonProperty("102")
    String flag102;

    @NotNull(message = "[consent_flag_103] must not be null")
    @Size(max = 1, message = "[consent_flag_103] must be no more than 1 characters")
    @JsonProperty("103")
    String flag103;

    @NotNull(message = "[consent_flag_104] must not be null")
    @Size(max = 1, message = "[consent_flag_104] must be no more than 1 characters")
    @JsonProperty("104")
    String flag104;

    @NotNull(message = "[consent_flag_105] must not be null")
    @Size(max = 1, message = "[consent_flag_105] must be no more than 1 characters")
    @JsonProperty("105")
    String flag105;

    @NotNull(message = "[consent_flag_106] must not be null")
    @Size(max = 1, message = "[consent_flag_106] must be no more than 1 characters")
    @JsonProperty("106")
    String flag106;

    @NotNull(message = "[consent_flag_107] must not be null")
    @Size(max = 1, message = "[consent_flag_107] must be no more than 1 characters")
    @JsonProperty("107")
    String flag107;
}
