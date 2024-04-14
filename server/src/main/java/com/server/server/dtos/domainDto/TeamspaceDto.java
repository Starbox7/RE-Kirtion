package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.models.TeamspaceModel;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamspaceDto {
    private String uuid;

    @JsonProperty("workspace_uuid")
    private String workspaceUuid;

    private String name;
    private String logo;

    private LocalDateTime created;

    public static TeamspaceDto fromModel(TeamspaceModel model) {
        TeamspaceDto dto = new TeamspaceDto();
        dto.setUuid(model.getUuid());
        dto.setWorkspaceUuid(model.getWorkspace() != null ? model.getWorkspace().getUuid() : null);
        dto.setName(model.getName());
        dto.setLogo(model.getLogo());
        dto.setCreated(model.getCreated());
        return dto;
    }

    // Static method to convert a list of TeamspaceModel to a list of TeamspaceDto
    public static List<TeamspaceDto> fromModelList(List<TeamspaceModel> models) {
        return models.stream()
                .map(TeamspaceDto::fromModel)
                .collect(Collectors.toList());
    }
}
