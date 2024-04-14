package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PageWebShareDto {
    private String uuid;

    @JsonProperty("page_uuid")
    private String pageUuid;

    private String url;
    private LocalDate register;
    private LocalDateTime expire;
    private boolean edit;
    private boolean comment;
    private boolean template;
    private boolean search;

    private LocalDateTime created;
}
