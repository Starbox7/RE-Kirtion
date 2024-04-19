package com.server.server.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.server.interfaces.IBlockRepo;
import com.server.server.interfaces.ICurrentRepo;
import com.server.server.interfaces.IPageRepo;
import com.server.server.interfaces.IPersonalspaceRepo;
import com.server.server.interfaces.ITeamspaceRepo;
import com.server.server.interfaces.IWorkspaceRepo;
import com.server.server.models.BlockModel;
import com.server.server.models.CurrentModel;
import com.server.server.models.PageModel;
import com.server.server.models.PersonalspaceModel;
import com.server.server.models.TeamspaceModel;
import com.server.server.models.WorkspaceModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpaceService {
  private final IPersonalspaceRepo iPersonalspaceRepo;
  private final ITeamspaceRepo iTeamspaceRepo;
  private final IPageRepo iPageRepo;
  private final ICurrentRepo iCurrentRepo;
  private final IWorkspaceRepo iWorkspaceRepo;
  private final IBlockRepo iBlockRepo;

  public PersonalspaceModel findPersonalspaceByUuid (String uuid) {
    Optional<PersonalspaceModel> personalspaceModel = iPersonalspaceRepo.findPersonalspaceByUuid(uuid);

    return personalspaceModel.get();
  }
  public PersonalspaceModel findPersonalspaceByWorkspaceUuid (String workspaceUuid){
    Optional<PersonalspaceModel> personalspaceModel = iPersonalspaceRepo.findPersonalspaceByWorkspaceUuid(workspaceUuid);

    return personalspaceModel.get();
  }

  public TeamspaceModel findTeamspaceByUuid (String uuid) {
    Optional<TeamspaceModel> teamspaceModel = iTeamspaceRepo.findTeamspaceByUuid(uuid);

    return teamspaceModel.get();
  }

  public PageModel findPageByUuid (String uuid) {
    Optional<PageModel> pageModel = iPageRepo.findPageByUuid(uuid);

    return pageModel.get();
  }

  public WorkspaceModel findWorkspaceByUserUuid(String userUuid){
    Optional<WorkspaceModel> workspaceModel =  iWorkspaceRepo.findWorkspaceByUserUuid(userUuid);

    return workspaceModel.get();
  }
  public WorkspaceModel findWorkspaceByWorkspaceUuid(String workspaceUuid){
    Optional<WorkspaceModel> workspaceModel =  iWorkspaceRepo.findWorkspaceByUuid(workspaceUuid);

    return workspaceModel.get();
  }

  public CurrentModel findCurrentByUserUuid (String userUuid){
    Optional<CurrentModel> currentModel = iCurrentRepo.findCurrentByUserUuid(userUuid);

    return currentModel.get();
  }

  public void updateCurrent (CurrentModel currentModel, PageModel pageModel, WorkspaceModel workspaceModel){
    currentModel.setPage(pageModel);
    currentModel.setWorkspace(workspaceModel);

    this.iCurrentRepo.save(currentModel);
  }

  public PageModel updatePage (PageModel pageModel, String title, String text){
    pageModel.setTitle(title);
    pageModel.setText(text);

    this.iPageRepo.save(pageModel);

    return pageModel;
  }

  public BlockModel findBlockByUuid (String uuid){

    Optional<BlockModel> blockModel = iBlockRepo.findBlockByUuid(uuid);

    return blockModel.get();
  }

  public void updateBlock (BlockModel blockModel, String type, String data){
    blockModel.setType(type);
    blockModel.setData(data);

    this.iBlockRepo.save(blockModel);
  }

  public void deleteBlock (BlockModel blockModel){
    this.iBlockRepo.delete(blockModel);
  }
}
