package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PersonalspaceDto {
    private String uuid;

    @JsonProperty("workspace_uuid")
    private String workspaceUuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    private LocalDateTime created;
}
