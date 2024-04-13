package com.server.server.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.server.server.dtos.InitDto;
import com.server.server.interfaces.ICurrentRepo;
import com.server.server.interfaces.IPageRepo;
import com.server.server.interfaces.IPageSettingRepo;
import com.server.server.interfaces.IPageSnapshotRepo;
import com.server.server.interfaces.IPageUpdateRepo;
import com.server.server.interfaces.IPersonalspaceRepo;
import com.server.server.interfaces.IUserSettingRepo;
import com.server.server.interfaces.IWorkspaceRepo;
import com.server.server.models.CurrentModel;
import com.server.server.models.PageModel;
import com.server.server.models.PageSettingModel;
import com.server.server.models.PageSnapshotModel;
import com.server.server.models.PageUpdateModel;
import com.server.server.models.PersonalspaceModel;
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
  
  public void kirtionInit (UserModel user, String name){
    WorkspaceModel workspace = new WorkspaceModel();

    workspace.setDomain(name);
    workspace.setLogo("https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1673081460/noticon/iqrrp7ziu0xuvltesgbs.png");
    workspace.setName(name+"'s workspace");
    workspace.setPlan("basic");
    workspace.setUser(user);

    this.iWorkspaceRepo.save(workspace);

    PersonalspaceModel personalspace = new PersonalspaceModel();

    personalspace.setUser(user);
    personalspace.setWorkspace(workspace);

    this.iPersonalspaceRepo.save(personalspace);

    PageModel page = new PageModel();

    page.setIcon("https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1707304224/noticon/hgxdzgbnrlbddahywgqr.png");
    page.setRoute("Welcome"); //보류
    page.setSoftDelete(false);
    page.setText("Welcome to Kirtion");
    page.setTitle("Welcome");
    page.setPersonalspace(personalspace);

    this.iPageRepo.save(page);

    CurrentModel current = new CurrentModel();

    current.setPage(page);
    current.setUser(user);
    current.setWorkspace(workspace);

    this.iCurrentRepo.save(current);

    PageSettingModel pageSetting = new PageSettingModel();

    pageSetting.setAlert(true);
    pageSetting.setBackLink("pop over");
    pageSetting.setComment(true);
    pageSetting.setExpansion(false);
    pageSetting.setFontFamily("normal");
    pageSetting.setFontSize(true);
    pageSetting.setWiki(false);
    pageSetting.setPageLock(false);
    pageSetting.setUpComment(true);
    pageSetting.setPage(page);

    this.iPageSettingRepo.save(pageSetting);

    PageSnapshotModel pageSnapshot = new PageSnapshotModel();

    pageSnapshot.setText(page.getText());
    pageSnapshot.setTitle(page.getTitle());
    pageSnapshot.setPage(page);

    this.iPageSnapshotRepo.save(pageSnapshot);

    PageUpdateModel pageUpdate = new PageUpdateModel();

    pageUpdate.setContent(page.getText());
    pageUpdate.setUser(user);
    pageUpdate.setPage(page);

    this.iPageUpdateRepo.save(pageUpdate);

    UserSettingModel userSetting = new UserSettingModel();

    userSetting.setAnalysisCookie(true);
    userSetting.setFunctionCookie(true);
    userSetting.setMarketingCookie(true);
    userSetting.setTheme("system");
    userSetting.setInitPage(true);
    userSetting.setAppLink(false);
    userSetting.setLocation(true);
    userSetting.setWorkspaceToEmail(true);
    userSetting.setTimeLine(82);
    userSetting.setSlackPush(false);
    userSetting.setMobilePush(true);
    userSetting.setUser(user);
    userSetting.setLanguage("English");
    userSetting.setMonday(true);
    userSetting.setEmailDescriptionPlan(true);
    userSetting.setEmailAlways(true);
    userSetting.setHistory(true);
    userSetting.setNotion(true);
    userSetting.setProfile(true);

    this.iUserSettingRepo.save(userSetting);
  }

  public InitDto currentInit(UserModel user){
    InitDto initDto = new InitDto();

    Optional<CurrentModel> currentOptional = iCurrentRepo.findCurrentByUser(user);
    Optional<PageModel> pageOptional = iPageRepo.findPageById(currentOptional.get().getPage());
    initDto.setCurrentPage(pageOptional.get());

    Optional<WorkspaceModel> workspacOptional = iWorkspaceRepo.findWorkspaceById(currentOptional.get().getWorkspace());
    initDto.setWorkspace(workspacOptional.get());

    Optional<PersonalspaceModel> personalspaceOptional = iPersonalspaceRepo.findPersonalspaceByWorkspace(workspacOptional.get());
    initDto.setPages(iPageRepo.findAllPageByPersonalspace(personalspaceOptional.get()));

    return null;
  }
}
