package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RicipientDto {
    private String uuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    private String name;
    private String country;
    private String address;

}
