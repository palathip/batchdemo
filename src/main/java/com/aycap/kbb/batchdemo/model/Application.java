package com.aycap.kbb.batchdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class Application {
    @NotEmpty(message = "[last_name] must not be empty")
    @Size(max = 20, message = "[application_no] must be no more than 20 characters")
    String applicationNo;

    @NotNull(message = "[product_code] must not be null")
    @Size(max = 5, message = "[product_code] must be no more than 5 characters")
    String productCode;

    @NotNull(message = "[sum_insured] must not be null")
    @Digits(integer = 13, fraction = 2,message = "[sum_insured] must in format (#############.##)")
    Double sumInsured;

    @NotNull(message = "[net_premium] must not be null")
    @Digits(integer = 13, fraction = 2,message = "[net_premium] must in format (#############.##)")
    Double netPremium;

    @NotNull(message = "[gross_premium] must not be null")
    @Digits(integer = 13, fraction = 2,message = "[gross_premium] must in format (#############.##)")
    Double grossPremium;

    @NotNull(message = "[effective_date] must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
    Date effectiveDate;

    @NotNull(message = "[expired_date] must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
    Date expiredDate;

    @NotNull(message = "[payer_title_name] must not be null")
    @Size(max = 40, message = "[payer_title_name] must be no more than 40 characters")
    String payerTitleName;

    /*Nullable*/
    @Size(max = 40, message = "[payer_title_name_en] must be no more than 40 characters")
    String payerTitleNameEn;

    @NotNull(message = "[payer_name] must not be null")
    @Size(max = 60, message = "[payer_name] must be no more than 60 characters")
    String payerName;

    /*Nullable*/
    @Size(max = 100, message = "[payer_name_en] must be no more than 100 characters")
    String payerNameEn;

    @NotNull(message = "[payer_lastname] must not be null")
    @Size(max = 65, message = "[payer_lastname] must be no more than 65 characters")
    String payerLastname;

    /*Nullable*/
    @Size(max = 100, message = "[payer_lastname_en] must be no more than 100 characters")
    String payerLastnameEn;

    @NotNull(message = "[payer_birth_date] must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
    Date payerBirthDate;

    @NotNull(message = "[payer_gender] must not be null")
    @Size(max = 1, message = "[payer_gender] must be no more than 1 characters")
    String payerGender;

    /*Nullable*/
    @Size(max = 1, message = "[payer_marriage_status] must be no more than 1 characters")
    String payerMarriageStatus;

    @NotNull(message = "[payer_type] must not be null")
    @Size(max = 1, message = "[payer_type] must be no more than 1 characters")
    String payerType;

    @NotNull(message = "[payer_card_type] must not be null")
    @Size(max = 1, message = "[payer_card_type] must be no more than 1 characters")
    String payerCardType;

    @NotNull(message = "[payer_card_id] must not be null")
    @Size(max = 20, message = "[payer_card_id] must be no more than 20 characters")
    String payerCardId;

    /*Nullable*/
    @Size(max = 50, message = "[payer_occupation] must be no more than 50 characters")
    String payerOccupation;

    /*Nullable*/
    @Size(max = 100, message = "[payer_moobarn] must be no more than 100 characters")
    String payerMoobarn;

    /*Nullable*/
    @Size(max = 10, message = "[payer_room_number] must be no more than 10 characters")
    String payerRoomNumber;

    @NotNull(message = "[payer_home_number] must not be null")
    @Size(max = 10, message = "[payer_home_number] must be no more than 10 characters")
    String payerHomeNumber;

    /*Nullable*/
    @Size(max = 3, message = "[payer_moo] must be no more than 3 characters")
    String payerMoo;

    /*Nullable*/
    @Size(max = 100, message = "[payer_soi] must be no more than 100 characters")
    String payerSoi;

    /*Nullable*/
    @Size(max = 100, message = "[payer_road] must be no more than 100 characters")
    String payerRoad;

    @NotNull(message = "[payer_tumbol] must not be null")
    @Size(max = 100, message = "[payer_tumbol] must be no more than 100 characters")
    String payerTumbol;

    @NotNull(message = "[payer_amphur] must not be null")
    @Size(max = 100, message = "[payer_amphur] must be no more than 100 characters")
    String payerAmphur;

    @NotNull(message = "[payer_province] must not be null")
    @Size(max = 100, message = "[payer_province] must be no more than 100 characters")
    String payerProvince;

    @NotNull(message = "[payer_post_code] must not be null")
    @Size(max = 10, message = "[payer_post_code] must be no more than 10 characters")
    String payerPostCode;

    /*Nullable*/
    @Size(max = 15, message = "[payer_telephone_extension] must be no more than 15 characters")
    String payerTelephoneExtension;

    @NotNull(message = "[payer_telephone] must not be null")
    @Size(max = 50, message = "[payer_telephone] must be no more than 50 characters")
    String payerTelephone;

    /*Nullable*/
    @Size(max = 20, message = "[payer_fax] must be no more than 20 characters")
    String payerFax;

    @NotNull(message = "[mode_of_payment] must not be null")
    @Size(max = 2, message = "[mode_of_payment] must be no more than 2 characters")
    String modeOfPayment;

    @NotNull(message = "[payment_channel] must not be null")
    @Size(max = 2, message = "[payment_channel] must be no more than 2 characters")
    String paymentChannel;

    //region  Optional With Condition  paymentChannel
    @AssertTrue(message = "[payment_sub_channel] must not be null")
    private boolean isPaymentChannelOmise(){
        if(paymentChannel.equals("10")){
            return this.paymentSubChannel != null;
        }
        return true;
    }
    //endregion
    @Size(max = 10, message = "[payment_sub_channel] must be no more than 10 characters")
    String paymentSubChannel;

    @NotNull(message = "[channel_flag] must not be null")
    @Size(max = 2, message = "[channel_flag] must be no more than 2 characters")
    String channelFlag;

    @NotNull(message = "[insurer_code] must not be null")
    @Size(max = 3, message = "[insurer_code] must be no more than 3 characters")
    String insurerCode;

    @NotNull(message = "[insurer_type] must not be null")
    @Size(max = 2, message = "[insurer_type] must be no more than 2 characters")
    String insurerType;

    /*Nullable*/
    @Size(max = 3, message = "[policy_year] must be no more than 3 characters")
    String policyYear;

    @NotNull(message = "[selling_date] must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
    Date sellingDate;

    /*Nullable*/
    @Size(max = 10, message = "[campaign_code] must be no more than 10 characters")
    String campaignCode;

    @NotNull(message = "[package_code] must not be null")
    @Size(max = 10, message = "[package_code] must be no more than 10 characters")
    String packageCode;

    @NotNull(message = "[subpackage_code] must not be null")
    @Size(max = 50, message = "[subpackage_code] must be no more than 50 characters")
    String subpackageCode;

    @NotNull(message = "[base_credit_card] must not be null")
    @Size(max = 16, message = "[base_credit_card] must be no more than 16 characters")
    String baseCreditCard;

    @NotNull(message = "[payer_consent_version] must not be null")
    @Size(max = 6, message = "[payer_consent_version] must be no more than 6 characters")
    String payerConsentVersion;

    //region waiting for implement

//    @NotNull(message = "[payer_consent_flags] must not be null")
//    /*Manual Review : unsure type consent_flags*/
//            Object payerConsentFlags;
//
//    /*Manual Review : Conditional Nullable*/
//    /*Manual Review : unsure type insured_person[]*/
//    Object insuredPersons;
//
//    @NotNull(message = "[payment_detail] must not be null")
//    /*Manual Review : unsure type payment_detail*/
//            Object paymentDetail;

    //endregion

    //region old version body
//    @NotEmpty(message = "[last_name] must not be empty")
//    @Size(max = 20, message = "[application_no] must be no more than 20 characters")
//    private String applicationNo;
//
//    @NotEmpty(message = "[last_name] must not be empty")
//    private String lastName;
//
//    @NotEmpty(message = "[first_name] must not be empty")
//    private String firstName;
//
//    @NotNull(message = "[application_no] must not be null")
//    @Size(max = 20, message = "[application_no] must be no more than 20 characters")
//    String applicationNo;
//
//    @NotNull(message = "[product_code] must not be null")
//    @Size(max = 5, message = "[product_code] must be no more than 5 characters")
//    String productCode;
//
//    @NotNull(message = "[sum_insured] must not be null")
//    @Digits(integer = 13, fraction = 2,message = "[sum_insured] must in format (#############.##)")
//    Double sumInsured;
//
//    @NotNull(message = "[net_premium] must not be null")
//    @Digits(integer = 13, fraction = 2,message = "[net_premium] must in format (#############.##)")
//    Double netPremium;
//
//    @NotNull(message = "[gross_premium] must not be null")
//    @Digits(integer = 13, fraction = 2,message = "[gross_premium] must in format (#############.##)")
//    Double grossPremium;
//
//    @NotNull(message = "[effective_date] must not be null")
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
//    Date effectiveDate;
//
//    @NotNull(message = "[expired_date] must not be null")
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
//    Date expiredDate;
//
//    @NotNull(message = "[payer_title_name] must not be null")
//    @Size(max = 40, message = "[payer_title_name] must be no more than 40 characters")
//    String payerTitleName;
//
//    /*Nullable*/
//    @Size(max = 40, message = "[payer_title_name_en] must be no more than 40 characters")
//    String payerTitleNameEn;
//
//    @NotNull(message = "[payer_name] must not be null")
//    @Size(max = 60, message = "[payer_name] must be no more than 60 characters")
//    String payerName;
//
//    /*Nullable*/
//    @Size(max = 100, message = "[payer_name_en] must be no more than 100 characters")
//    String payerNameEn;
//
//    @NotNull(message = "[payer_lastname] must not be null")
//    @Size(max = 65, message = "[payer_lastname] must be no more than 65 characters")
//    String payerLastname;
//
//    /*Nullable*/
//    @Size(max = 100, message = "[payer_lastname_en] must be no more than 100 characters")
//    String payerLastnameEn;
//
//    @NotNull(message = "[payer_birth_date] must not be null")
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
//    Date payerBirthDate;
//
//    @NotNull(message = "[payer_gender] must not be null")
//    @Size(max = 1, message = "[payer_gender] must be no more than 1 characters")
//    String payerGender;
//
//    /*Nullable*/
//    @Size(max = 1, message = "[payer_marriage_status] must be no more than 1 characters")
//    String payerMarriageStatus;
//
//    @NotNull(message = "[payer_type] must not be null")
//    @Size(max = 1, message = "[payer_type] must be no more than 1 characters")
//    String payerType;
//
//    @NotNull(message = "[payer_card_type] must not be null")
//    @Size(max = 1, message = "[payer_card_type] must be no more than 1 characters")
//    String payerCardType;
//
//    @NotNull(message = "[payer_card_id] must not be null")
//    @Size(max = 20, message = "[payer_card_id] must be no more than 20 characters")
//    String payerCardId;
//
//    /*Nullable*/
//    @Size(max = 50, message = "[payer_occupation] must be no more than 50 characters")
//    String payerOccupation;
//
//    /*Nullable*/
//    @Size(max = 100, message = "[payer_moobarn] must be no more than 100 characters")
//    String payerMoobarn;
//
//    /*Nullable*/
//    @Size(max = 10, message = "[payer_room_number] must be no more than 10 characters")
//    String payerRoomNumber;
//
//    @NotNull(message = "[payer_home_number] must not be null")
//    @Size(max = 10, message = "[payer_home_number] must be no more than 10 characters")
//    String payerHomeNumber;
//
//    /*Nullable*/
//    @Size(max = 3, message = "[payer_moo] must be no more than 3 characters")
//    String payerMoo;
//
//    @NotNull(message = "[payer_soi] must not be null")
//    @Size(max = 100, message = "[payer_soi] must be no more than 100 characters")
//    String payerSoi;
//
//    /*Nullable*/
//    @Size(max = 100, message = "[payer_road] must be no more than 100 characters")
//    String payerRoad;
//
//    @NotNull(message = "[payer_tumbol] must not be null")
//    @Size(max = 100, message = "[payer_tumbol] must be no more than 100 characters")
//    String payerTumbol;
//
//    @NotNull(message = "[payer_amphur] must not be null")
//    @Size(max = 100, message = "[payer_amphur] must be no more than 100 characters")
//    String payerAmphur;
//
//    @NotNull(message = "[payer_province] must not be null")
//    @Size(max = 100, message = "[payer_province] must be no more than 100 characters")
//    String payerProvince;
//
//    @NotNull(message = "[payer_post_code] must not be null")
//    @Size(max = 10, message = "[payer_post_code] must be no more than 10 characters")
//    String payerPostCode;
//
//    /*Nullable*/
//    @Size(max = 15, message = "[payer_telephone_extension] must be no more than 15 characters")
//    String payerTelephoneExtension;
//
//    @NotNull(message = "[payer_telephone] must not be null")
//    @Size(max = 50, message = "[payer_telephone] must be no more than 50 characters")
//    String payerTelephone;
//
//    /*Nullable*/
//    @Size(max = 20, message = "[payer_fax] must be no more than 20 characters")
//    String payerFax;
//
//    @NotNull(message = "[mode_of_payment] must not be null")
//    @Size(max = 2, message = "[mode_of_payment] must be no more than 2 characters")
//    String modeOfPayment;
//
//    @NotNull(message = "[payment_channel] must not be null")
//    @Size(max = 2, message = "[payment_channel] must be no more than 2 characters")
//    String paymentChannel;
//
//
//    //region  Optional With Condition  paymentChannel
//    @AssertTrue(message = "[payment_sub_channel] must not be null")
//    private boolean isPaymentChannelOmise(){
//        if(paymentChannel.equals("10")){
//            return this.paymentSubChannel != null;
//        }
//        return true;
//    }
//
//    @Size(max = 10, message = "[payment_sub_channel] must be no more than 10 characters")
//    String paymentSubChannel;
//    //endregion
//
//    @NotNull(message = "[channel_flag] must not be null")
//    @Size(max = 2, message = "[channel_flag] must be no more than 2 characters")
//    String channelFlag;
//
//    @NotNull(message = "[insurer_code] must not be null")
//    @Size(max = 3, message = "[insurer_code] must be no more than 3 characters")
//    String insurerCode;
//
//    @NotNull(message = "[insurer_type] must not be null")
//    @Size(max = 2, message = "[insurer_type] must be no more than 2 characters")
//    String insurerType;
//
//    //region  Optional With Condition  insurerType
//
//    @AssertTrue(message = "[policy_year] must not be null")
//    private boolean isInsurerTypeLife(){
//        if(insurerType.equals("LF")){
//            return policyYear != null;
//        }
//        return true;
//    }
//
//    @Size(max = 3, message = "[policy_year] must be no more than 3 characters")
//    String policyYear;
//
//    //endregion
//
//    @NotNull(message = "[selling_date] must not be null")
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Bangkok")
//    Date sellingDate;
//
//    /*Nullable*/
//    @Size(max = 10, message = "[campaign_code] must be no more than 10 characters")
//    String campaignCode;
//
//    @NotNull(message = "[package_code] must not be null")
//    @Size(max = 10, message = "[package_code] must be no more than 10 characters")
//    String packageCode;
//
//    @NotNull(message = "[subpackage_code] must not be null")
//    @Size(max = 50, message = "[subpackage_code] must be no more than 50 characters")
//    String subpackageCode;
//
//    @NotNull(message = "[base_credit_card] must not be null")
//    @Size(max = 16, message = "[base_credit_card] must be no more than 16 characters")
//    String baseCreditCard;
//
//    @NotNull(message = "[payer_consent_version] must not be null")
//    @Size(max = 6, message = "[payer_consent_version] must be no more than 6 characters")
//    String payerConsentVersion;
//
//    @NotNull(message = "[payer_consent_flags] must not be null")
//    @Size(max = 10, message = "[payer_consent_flags] must be no more than 10 characters")
//    String payerConsentFlags;
//
//    //todo  /*Manual Review : Conditional Nullable*/ data sheet no condition ?
//    List<@Valid InsuredPerson> insuredPersons;
//
//    @NotNull(message = "[payment_detail] must not be null")
//    //todo   /*Manual Review : unsure type payment_detail*/ change to object
//    OmiseResult paymentDetail;

    //endregion

}
