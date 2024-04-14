package com.server.server.dtos.domainDto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentDto {
    private String uuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    @JsonProperty("page_uuid")
    private String pageUuid;

    @JsonProperty("workspace_uuid")
    private String workspaceUuid;
}
