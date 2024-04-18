package com.server.server.services;

import org.springframework.stereotype.Service;

import com.server.server.interfaces.IBlockRepo;
import com.server.server.interfaces.ICurrentRepo;
import com.server.server.interfaces.IMessageRepo;
import com.server.server.interfaces.IPageRepo;
import com.server.server.interfaces.IPageSettingRepo;
import com.server.server.interfaces.IPageSnapshotRepo;
import com.server.server.interfaces.IPageUpdateRepo;
import com.server.server.interfaces.IPageWebShareRepo;
import com.server.server.interfaces.IPaymentRepo;
import com.server.server.interfaces.IPersonalspaceRepo;
import com.server.server.interfaces.IRicipientRepo;
import com.server.server.interfaces.ITeamMemberRepo;
import com.server.server.interfaces.ITeamspaceRepo;
import com.server.server.interfaces.IUserRepo;
import com.server.server.interfaces.IUserSettingRepo;
import com.server.server.interfaces.IVatRepo;
import com.server.server.interfaces.IWorkspaceRepo;
import com.server.server.models.BlockModel;
import com.server.server.models.CurrentModel;
import com.server.server.models.MessageModel;
import com.server.server.models.PageModel;
import com.server.server.models.PageSettingModel;
import com.server.server.models.PageSnapshotModel;
import com.server.server.models.PageUpdateModel;
import com.server.server.models.PageWebShareModel;
import com.server.server.models.PaymentModel;
import com.server.server.models.PersonalspaceModel;
import com.server.server.models.RicipientModel;
import com.server.server.models.TeamMemberModel;
import com.server.server.models.TeamspaceModel;
import com.server.server.models.UserModel;
import com.server.server.models.UserSettingModel;
import com.server.server.models.VatModel;
import com.server.server.models.WorkspaceModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitModelService {
  private final ICurrentRepo iCurrentRepo;
  private final IMessageRepo iMessageRepo;
  private final IPageRepo iPageRepo;
  private final IPageSettingRepo iPageSettingRepo;
  private final IPageSnapshotRepo iPageSnapshotRepo;
  private final IPageUpdateRepo iPageUpdateRepo;
  private final IPageWebShareRepo iPageWebShareRepo;
  private final IPaymentRepo iPaymentRepo;
  private final IPersonalspaceRepo iPersonalspaceRepo;
  private final IRicipientRepo iRicipientRepo;
  private final ITeamMemberRepo iTeamMemberRepo;
  private final ITeamspaceRepo iTeamspaceRepo;
  private final IUserRepo iUserRepo;
  private final IUserSettingRepo iUserSettingRepo;
  private final IVatRepo iVatRepo;
  private final IWorkspaceRepo iWorkspaceRepo;
  private final IBlockRepo iBlockRepo;

  private final AuthService authService;

    public CurrentModel currentInit(PageModel page, UserModel user, WorkspaceModel workspace) {
        CurrentModel current = new CurrentModel();

        current.setPage(page);
        current.setUser(user);
        current.setWorkspace(workspace);

        this.iCurrentRepo.save(current);

        return current;
    }

    public MessageModel messageInit() {
        return null;
    }

    public PageModel pageInit(PersonalspaceModel personalspace, TeamspaceModel teamspace, PageModel page) {
        PageModel newPage = new PageModel();

        newPage.setIcon("https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1707304224/noticon/hgxdzgbnrlbddahywgqr.png");
        newPage.setRoute("Title"); //보류
        newPage.setSoftDelete(false);
        newPage.setText("Text Input Area");
        newPage.setTitle("Title");
        newPage.setBackground("https://images.unsplash.com/photo-1712617137312-343869c90743?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        if(personalspace != null){
            newPage.setPersonalspace(personalspace);
        }
        else if(teamspace != null){
            newPage.setTeamspace(teamspace);  
        }
        else if(page != null){
            newPage.setPage(page);
        }

        this.iPageRepo.save(newPage);

        return newPage;
    }

    public BlockModel blockInit(PageModel pageModel){
        BlockModel blockModel = new BlockModel();

        blockModel.setPage(pageModel);
        blockModel.setType("text");
        blockModel.setData("");
        blockModel.setCount(1);

        iBlockRepo.save(blockModel);

        return blockModel;
    }

    public PageSettingModel pageSettingInit(PageModel page) {
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

        return pageSetting;
    }

    public PageSnapshotModel pageSnapshotInit(PageModel page) {
        PageSnapshotModel pageSnapshot = new PageSnapshotModel();

        pageSnapshot.setText(page.getText());
        pageSnapshot.setTitle(page.getTitle());
        pageSnapshot.setPage(page);

        this.iPageSnapshotRepo.save(pageSnapshot);

        return pageSnapshot;
    }

    public PageUpdateModel pageUpdateInit(PageModel page, UserModel user) {
        PageUpdateModel pageUpdate = new PageUpdateModel();

        pageUpdate.setContent(page.getText());
        pageUpdate.setUser(user);
        pageUpdate.setPage(page);

        this.iPageUpdateRepo.save(pageUpdate);  

        return pageUpdate;
    }

    public PageWebShareModel pageWebShareInit() {
        return null;
    }

    public PaymentModel paymentInit() {
        return null;
    }

    public PersonalspaceModel personalspaceInit(UserModel user, WorkspaceModel workspace) {
        PersonalspaceModel personalspace = new PersonalspaceModel();

        personalspace.setUser(user);
        personalspace.setWorkspace(workspace);

        this.iPersonalspaceRepo.save(personalspace);

        return personalspace;
    }

    public RicipientModel ricipientInit() {
        return null;
    }

    public TeamMemberModel teamMemberInit() {
        return null;
    }

    public TeamspaceModel teamspaceInit() {
        return null;
    }

    public UserModel userInit() {
        return null;
    }

    public UserSettingModel userSettingInit(UserModel user) {
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

        return userSetting;
    }

    public VatModel vatInit() {
        return null;
    }

    public WorkspaceModel workspaceInit(UserModel user) {
        String email = user.getEmail();
        String name = authService.createNameByEmail(email);

        WorkspaceModel workspace = new WorkspaceModel();

        workspace.setDomain(name);
        workspace.setLogo("https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1673081460/noticon/iqrrp7ziu0xuvltesgbs.png");
        workspace.setName(name+"'s workspace");
        workspace.setPlan("basic");
        workspace.setUser(user);

        this.iWorkspaceRepo.save(workspace);

        return workspace;
    }
}
