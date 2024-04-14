package com.server.server.dtos;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.dtos.domainDto.PageDto;
import com.server.server.dtos.domainDto.TeamspaceDto;
import com.server.server.dtos.domainDto.WorkspaceDto;

import lombok.Data;

@Data
public class InitDto {
    
    @JsonProperty(value = "current_page")
    private PageDto currentPage = new PageDto();
    @JsonProperty(value = "current_workspace")
    private WorkspaceDto currentWorkspace = new WorkspaceDto();
    @JsonProperty(value = "personal_page_list")
    private List<PageDto> personalPageList = new ArrayList<>();
    @JsonProperty(value = "page_list_in_teamspace_list")
    private List<PageListInTeamspace> pageListInTeamspacesList = new ArrayList<>();

    @Data
    public static class PageListInTeamspace {
        private TeamspaceDto teamspace;
        @JsonProperty(value = "teamspace_page_list")
        private List<PageDto> teamspacePageList = new ArrayList<>();
    }
}
