package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentDto {
    private String uuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    @JsonProperty("card_num")
    private int cardNumber;

    private int expire;
    private int cvc;
    private String country;
}
