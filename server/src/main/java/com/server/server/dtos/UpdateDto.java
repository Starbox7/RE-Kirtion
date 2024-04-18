package com.server.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateDto {
  @JsonProperty(value = "page_uuid")
  private String pageUuid;
  private String title;
  private String text;
}
