package com.server.server.dtos.domainDto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDto {
    private String uuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    private String title;

    private LocalDateTime created;
}
