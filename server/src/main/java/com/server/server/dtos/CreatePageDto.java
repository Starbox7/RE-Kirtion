package com.server.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreatePageDto {
  @JsonProperty(value = "workspace_uuid")
  private String workspaceUuid;
}
