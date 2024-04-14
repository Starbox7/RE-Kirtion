package com.server.server.dtos.domainDto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.models.WorkspaceModel;

import lombok.Data;

@Data
public class WorkspaceDto {
    private String uuid;
    @JsonProperty("user_uuid")
    private String userUuid;
    private String name;
    private String logo;
    private String plan;
    private String domain;
    private LocalDateTime created;  

    public static WorkspaceDto fromModel(WorkspaceModel model) {
        WorkspaceDto dto = new WorkspaceDto();
        dto.setUuid(model.getUuid());
        dto.setUserUuid(model.getUser() != null ? model.getUser().getUuid() : null);
        dto.setName(model.getName());
        dto.setLogo(model.getLogo());
        dto.setPlan(model.getPlan());
        dto.setDomain(model.getDomain());
        dto.setCreated(model.getCreated());
        return dto;
    }
}
