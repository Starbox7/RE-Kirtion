package com.server.server.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.server.models.PageModel;
import com.server.server.models.TeamMemberModel;
import com.server.server.models.TeamspaceModel;
import com.server.server.models.WorkspaceModel;

import lombok.Data;

@Data
public class InitDto {
    private PageModel currentPage;
    private WorkspaceModel workspace;

    @JsonProperty("personalspace_page_list")
    private List<PageModel> pages = new ArrayList<>();

    @JsonProperty("teamspace_list")
    private List<TeamspaceDto> teamspaces = new ArrayList<>();

    public static class TeamspaceDto {
        @JsonProperty("teamspace")
        private TeamspaceModel teamspace;

        @JsonProperty("teamspace_page_list")
        private List<PageModel> pages = new ArrayList<>();
    }
}

