package com.server.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SelectPageDto {
  @JsonProperty(value = "page_uuid")
  private String pageUuid;
  @JsonProperty(value = "workspace_uuid")
  private String workspaceUuid;
}
