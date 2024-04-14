package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PageSnapshotDto {
    private String uuid;
    
    private LocalDateTime created;
    
    @JsonProperty("page_uuid")
    private String pageUuid;
    
    private String icon;
    private String title;
    
    private String text;
}
