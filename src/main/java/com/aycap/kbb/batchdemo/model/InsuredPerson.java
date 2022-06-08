package com.aycap.kbb.batchdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InsuredPerson {
    @NotNull(message = "[insured_title_name] must not be null")
    @Size(max = 40, message = "[insured_title_name] must be no more than 40 characters")
    String insuredTitleName;

    /*Nullable*/
    @Size(max = 40, message = "[insured_title_name_en] must be no more than 40 characters")
    String insuredTitleNameEn;

    @NotNull(message = "[insured_name] must not be null")
    @Size(max = 60, message = "[insured_name] must be no more than 60 characters")
    String insuredName;

    /*Nullable*/
    @Size(max = 100, message = "[insured_name_en] must be no more than 100 characters")
    String insuredNameEn;

    @NotNull(message = "[insured_lastname] must not be null")
    @Size(max = 65, message = "[insured_lastname] must be no more than 65 characters")
    String insuredLastname;

    /*Nullable*/
    @Size(max = 100, message = "[insured_lastname_en] must be no more than 100 characters")
    String insuredLastnameEn;

    @NotNull(message = "[insured_birth_date] must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
    Date insuredBirthDate;

    @NotNull(message = "[insured_age] must not be null")
    Long insuredAge;

    @NotNull(message = "[insured_gender] must not be null")
    @Size(max = 1, message = "[insured_gender] must be no more than 1 characters")
    String insuredGender;

    /*Nullable*/
    @Size(max = 1, message = "[insured_marriage_status] must be no more than 1 characters")
    String insuredMarriageStatus;

    @NotNull(message = "[insured_type] must not be null")
    @Size(max = 1, message = "[insured_type] must be no more than 1 characters")
    String insuredType;

    @NotNull(message = "[insured_card_type] must not be null")
    @Size(max = 1, message = "[insured_card_type] must be no more than 1 characters")
    String insuredCardType;

    @NotNull(message = "[insured_card_id] must not be null")
    @Size(max = 20, message = "[insured_card_id] must be no more than 20 characters")
    String insuredCardId;

    /*Nullable*/
    @Size(max = 50, message = "[insured_ocupation] must be no more than 50 characters")
    String insuredOcupation;

    /*Nullable*/
    @Size(max = 100, message = "[insured_moobarn] must be no more than 100 characters")
    String insuredMoobarn;

    /*Nullable*/
    @Size(max = 10, message = "[insured_room_number] must be no more than 10 characters")
    String insuredRoomNumber;

    @NotNull(message = "[insured_home_number] must not be null")
    @Size(max = 10, message = "[insured_home_number] must be no more than 10 characters")
    String insuredHomeNumber;

    /*Nullable*/
    @Size(max = 3, message = "[insured_moo] must be no more than 3 characters")
    String insuredMoo;

    /*Nullable*/
    @Size(max = 100, message = "[insured_soi] must be no more than 100 characters")
    String insuredSoi;

    /*Nullable*/
    @Size(max = 100, message = "[insured_road] must be no more than 100 characters")
    String insuredRoad;

    @NotNull(message = "[insured_tumbol] must not be null")
    @Size(max = 100, message = "[insured_tumbol] must be no more than 100 characters")
    String insuredTumbol;

    @NotNull(message = "[insured_amphur] must not be null")
    @Size(max = 100, message = "[insured_amphur] must be no more than 100 characters")
    String insuredAmphur;

    @NotNull(message = "[insured_province] must not be null")
    @Size(max = 100, message = "[insured_province] must be no more than 100 characters")
    String insuredProvince;

    @NotNull(message = "[insured_post_code] must not be null")
    @Size(max = 10, message = "[insured_post_code] must be no more than 10 characters")
    String insuredPostCode;

    /*Nullable*/
    @Size(max = 15, message = "[insured_telephone_extension] must be no more than 15 characters")
    String insuredTelephoneExtension;

    @NotNull(message = "[insured_telephone] must not be null")
    @Size(max = 50, message = "[insured_telephone] must be no more than 50 characters")
    String insuredTelephone;

    /*Nullable*/
    @Size(max = 13, message = "[insured_fax] must be no more than 13 characters")
    String insuredFax;

    @NotNull(message = "[insured_consent_version] must not be null")
    @Size(max = 6, message = "[insured_consent_version] must be no more than 6 characters")
    String insuredConsentVersion;

    @NotNull(message = "[insured_consent_flags] must not be null")
    @Valid
    ConsentFlag insuredConsentFlags;
}
