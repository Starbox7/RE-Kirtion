package com.server.server.dtos.domainDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.models.PageModel;

import lombok.Data;

@Data
public class PageDto {
        private String uuid;
        private String title;
        private String icon;
        private String background;
        private String text;
        private LocalDateTime created;
        @JsonProperty("soft_delete")
        private boolean softDelete;
        private String route;

        @JsonProperty("personalspace_uuid")
        private String personalspaceUuid;
        @JsonProperty("teamspace_uuid")
        private String teamspaceUuid;
        @JsonProperty("parent_page_uuid")
        private String parentPageUuid; 

        public static PageDto fromModel(PageModel page) {
                PageDto dto = new PageDto();
                dto.setUuid(page.getUuid());
                dto.setTitle(page.getTitle());
                dto.setIcon(page.getIcon());
                dto.setBackground(page.getBackground());
                dto.setText(page.getText());
                dto.setCreated(page.getCreated());
                dto.setSoftDelete(page.isSoftDelete());
                dto.setRoute(page.getRoute());
                if (page.getPersonalspace() != null) {
                dto.setPersonalspaceUuid(page.getPersonalspace().getUuid());
                }
                if (page.getTeamspace() != null) {
                dto.setTeamspaceUuid(page.getTeamspace().getUuid());
                }
                if (page.getPage() != null) {
                dto.setParentPageUuid(page.getPage().getUuid());
                }
                return dto;
        }

        public static List<PageDto> fromModelList(List<PageModel> models) {
        return models.stream()
                .map(PageDto::fromModel)
                .collect(Collectors.toList());
        }
}
