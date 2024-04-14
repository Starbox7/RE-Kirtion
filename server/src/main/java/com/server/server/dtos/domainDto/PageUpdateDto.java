package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageUpdateDto {
    private String uuid;

    @JsonProperty("page_uuid")
    private String pageUuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    private LocalDateTime updated;

    private String content;
}
