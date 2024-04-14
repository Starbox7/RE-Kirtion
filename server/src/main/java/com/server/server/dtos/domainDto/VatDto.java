package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VatDto {
    private String uuid;
    @JsonProperty("user_uuid")
    private String userUuid;
    private int number;
}
