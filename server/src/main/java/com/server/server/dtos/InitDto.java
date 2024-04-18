package com.server.server.dtos;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.dtos.domainDto.BlockDto;
import com.server.server.dtos.domainDto.PageDto;
import com.server.server.dtos.domainDto.TeamspaceDto;
import com.server.server.dtos.domainDto.WorkspaceDto;

import lombok.Data;

@Data
public class InitDto {
    
    @JsonProperty(value = "current_page")
    private PageDto currentPage = new PageDto();
    @JsonProperty(value = "current_page_block_list")
    private List<BlockDto> currentPageBlockList = new ArrayList<>();

    @JsonProperty(value = "current_workspace")
    private WorkspaceDto currentWorkspace = new WorkspaceDto();

    // @JsonProperty(value = "personal_page_list")
    // private List<PageDto> personalPageList = new ArrayList<>();

    @JsonProperty(value = "block_list_in_personal_page_list")
    private List<BlockListInPersonalPage> blockListInPersonalPageList = new ArrayList<>();

    @JsonProperty(value = "page_list_in_teamspace_list")
    private List<PageListInTeamspace> pageListInTeamspacesList = new ArrayList<>();

    //팀 스페이스 블록 처리 보류 
    @Data
    public static class PageListInTeamspace {
        private TeamspaceDto teamspace;
        @JsonProperty(value = "teamspace_page_list")
        private List<PageDto> teamspacePageList = new ArrayList<>();
    }

    @Data
    public static class  BlockListInPersonalPage{
        private PageDto personalPage = new PageDto();
        @JsonProperty(value = "personal_page_block_list")
        private List<BlockDto> personalPageBlockList = new ArrayList<>();
    }
}
