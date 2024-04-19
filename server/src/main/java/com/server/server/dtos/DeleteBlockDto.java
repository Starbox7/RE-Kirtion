package com.server.server.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeleteBlockDto {
  @JsonProperty(value = "block_uuid")
  private String blockUuid;
}
