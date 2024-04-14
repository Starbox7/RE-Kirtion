package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamMemberDto {
    private String uuid;

    @JsonProperty("teamspace_uuid")
    private String teamspaceUuid;

    @JsonProperty("user_uuid")
    private String userUuid;

    private LocalDateTime created;

}
