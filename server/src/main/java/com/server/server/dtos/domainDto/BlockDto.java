package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.models.BlockModel;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BlockDto {
  private String uuid;
  @JsonProperty("page_uuid")
  private String pageUuid;
  private String type;
  private String data;
  private int count;


      public static List<BlockDto> fromModelList(List<BlockModel> models) {
        return models.stream().map(model -> {
            BlockDto dto = new BlockDto();
            dto.setUuid(model.getUuid());
            dto.setPageUuid(model.getPage().getUuid());
            dto.setType(model.getType());
            dto.setData(model.getData());
            dto.setCount(model.getCount());
            return dto;
        }).collect(Collectors.toList());
        }
}
