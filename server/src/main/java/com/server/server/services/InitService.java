package com.server.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.server.dtos.InitDto;
import com.server.server.dtos.InitDto.BlockListInPersonalPage;
import com.server.server.dtos.InitDto.PageListInTeamspace;
import com.server.server.dtos.domainDto.BlockDto;
import com.server.server.dtos.domainDto.PageDto;
import com.server.server.dtos.domainDto.TeamspaceDto;
import com.server.server.dtos.domainDto.WorkspaceDto;
import com.server.server.interfaces.IBlockRepo;
import com.server.server.interfaces.ICurrentRepo;
import com.server.server.interfaces.IPageRepo;
import com.server.server.interfaces.IPageSettingRepo;
import com.server.server.interfaces.IPageSnapshotRepo;
import com.server.server.interfaces.IPageUpdateRepo;
import com.server.server.interfaces.IPersonalspaceRepo;
import com.server.server.interfaces.ITeamspaceRepo;
import com.server.server.interfaces.IUserSettingRepo;
import com.server.server.interfaces.IWorkspaceRepo;
import com.server.server.models.BlockModel;
import com.server.server.models.CurrentModel;
import com.server.server.models.PageModel;
import com.server.server.models.PageSettingModel;
import com.server.server.models.PageSnapshotModel;
import com.server.server.models.PageUpdateModel;
import com.server.server.models.PersonalspaceModel;
import com.server.server.models.TeamspaceModel;
import com.server.server.models.UserModel;
import com.server.server.models.UserSettingModel;
import com.server.server.models.WorkspaceModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitService {
  private final IWorkspaceRepo iWorkspaceRepo;
  private final IPersonalspaceRepo iPersonalspaceRepo;
  private final IPageRepo iPageRepo;
  private final ICurrentRepo iCurrentRepo;
  private final IPageSettingRepo iPageSettingRepo;
  private final IPageSnapshotRepo iPageSnapshotRepo;
  private final IPageUpdateRepo iPageUpdateRepo;
  private final IUserSettingRepo iUserSettingRepo;
  private final ITeamspaceRepo iTeamspaceRepo;
  private final IBlockRepo iBlockRepo;

  private final InitModelService initModelService;
  
  public void kirtionInit (UserModel user){
    initModelService.userSettingInit(user);
    WorkspaceModel workspace = initModelService.workspaceInit(user);
    PersonalspaceModel personalspace = initModelService.personalspaceInit(user, workspace);

    PageModel welcomePage = initModelService.pageInit(personalspace, null, null);
    welcomePage.setRoute("Welcome"); //보류
    welcomePage.setText("");
    welcomePage.setTitle("Start Kirtion");
    welcomePage.setBackground("https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=3200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
    this.iPageRepo.save(welcomePage);
    initModelService.pageSettingInit(welcomePage);
    initModelService.pageSnapshotInit(welcomePage);
    initModelService.pageUpdateInit(welcomePage, user);

    BlockModel block = new BlockModel();
    block.setPage(welcomePage);
    block.setType("text");
    block.setCount(1);
    block.setData("Welcome to Kirtion");
    this.iBlockRepo.save(block);

    PageModel infoPage = initModelService.pageInit(personalspace, null, welcomePage);
    infoPage.setRoute("Information");
    infoPage.setText("How to use Kirtion?"); 
    infoPage.setTitle("Kirtion Docs");
    infoPage.setBackground("https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=3200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
    this.iPageRepo.save(infoPage);
    initModelService.pageSettingInit(infoPage);
    initModelService.pageSnapshotInit(infoPage);
    initModelService.pageUpdateInit(infoPage, user);

    BlockModel block2 = new BlockModel();
    block2.setPage(infoPage);
    block2.setType("text");
    block2.setCount(1);
    block2.setData("How to use Kirtion?");
    this.iBlockRepo.save(block2);

    initModelService.currentInit(welcomePage, user, workspace);
  }

  public InitDto currentInit(UserModel user){
    InitDto initDto = new InitDto();

    Optional<CurrentModel> currentOptional = iCurrentRepo.findCurrentByUserUuid(user.getUuid());
    Optional<PageModel> pageOptional = iPageRepo.findPageByUuid(currentOptional.get().getPage().getUuid());
    PageDto pageDto = PageDto.fromModel(pageOptional.get());
    initDto.setCurrentPage(pageDto);

    List<BlockModel> blockModels = iBlockRepo.findAllBlockByPageUuid(pageDto.getUuid());
    List<BlockDto> currentBlockDtos = BlockDto.fromModelList(blockModels);
    initDto.setCurrentPageBlockList(currentBlockDtos);


    Optional<WorkspaceModel> workspaceOptional = iWorkspaceRepo.findWorkspaceByUuid(currentOptional.get().getWorkspace().getUuid());
    WorkspaceDto workspaceDto = WorkspaceDto.fromModel(workspaceOptional.get());
    initDto.setCurrentWorkspace(workspaceDto);

    // Optional<PersonalspaceModel> personalspaceOptional = iPersonalspaceRepo.findPersonalspaceByWorkspaceUuid(workspaceOptional.get().getUuid());
    // List<PageModel> personalPageModels = iPageRepo.findAllPageByPersonalspaceUuid(personalspaceOptional.get().getUuid());
    // List<PageDto> personalPageDtos = PageDto.fromModelList(personalPageModels);
    // initDto.setPersonalPageList(personalPageDtos);

    Optional<PersonalspaceModel> personalspaceOptional = iPersonalspaceRepo.findPersonalspaceByWorkspaceUuid(workspaceOptional.get().getUuid());

        if (personalspaceOptional.isPresent()) {
            List<PageModel> personalPageModels = iPageRepo.findAllPageByPersonalspaceUuid(personalspaceOptional.get().getUuid());
            List<BlockListInPersonalPage> blockListInPersonalPageList = new ArrayList<>();

            for (PageModel page : personalPageModels) {
                BlockListInPersonalPage blockListInPersonalPage = new BlockListInPersonalPage();
                blockListInPersonalPage.setPersonalPage(PageDto.fromModel(page));
                List<BlockDto> blockDtos = BlockDto.fromModelList(iBlockRepo.findAllBlockByPageUuid(page.getUuid()));
                blockListInPersonalPage.setPersonalPageBlockList(blockDtos);
                blockListInPersonalPageList.add(blockListInPersonalPage);
            }

            initDto.setBlockListInPersonalPageList(blockListInPersonalPageList);
          }

    List<TeamspaceModel> teamspaceModels = iTeamspaceRepo.findTAllTeamspaceByWorkspaceUuid(workspaceOptional.get().getUuid());
    List<TeamspaceDto> teamspaceDtos = TeamspaceDto.fromModelList(teamspaceModels);
    List<PageListInTeamspace> pageListInTeamspaces = new ArrayList<>();

    for (TeamspaceDto teamspaceDto : teamspaceDtos){
      List<PageModel> teamspacePageModels = iPageRepo.findAllPageByTeamspaceUuid(teamspaceDto.getUuid());
      List<PageDto> teamspacePageDtos = PageDto.fromModelList(teamspacePageModels);
      PageListInTeamspace pageListInTeamspace = new PageListInTeamspace();
      pageListInTeamspace.setTeamspace(teamspaceDto);
      pageListInTeamspace.setTeamspacePageList(teamspacePageDtos);

      pageListInTeamspaces.add(pageListInTeamspace);
    }
    initDto.setPageListInTeamspacesList(pageListInTeamspaces);

    return initDto;
  }
}
