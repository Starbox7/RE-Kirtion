package com.server.server.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.server.dtos.CreatePageDto;
import com.server.server.dtos.InitDto;
import com.server.server.dtos.SelectPageDto;
import com.server.server.dtos.UpdateBlockDto;
import com.server.server.dtos.UpdateDto;
import com.server.server.dtos.DeleteBlockDto;
import com.server.server.dtos.domainDto.BlockDto;
import com.server.server.dtos.domainDto.PageDto;
import com.server.server.global.auth.JwtTokenProvider;
import com.server.server.models.BlockModel;
import com.server.server.models.CurrentModel;
import com.server.server.models.PageModel;
import com.server.server.models.PersonalspaceModel;
import com.server.server.models.TeamspaceModel;
import com.server.server.models.UserModel;
import com.server.server.models.WorkspaceModel;
import com.server.server.services.AuthService;
import com.server.server.services.InitModelService;
import com.server.server.services.InitService;
import com.server.server.services.SpaceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/space")
public class SpaceController {
  private final JwtTokenProvider jwtTokenProvider;

  private final AuthService authService;
  private final InitService initService;
  private final SpaceService spaceService;
  private final InitModelService initModelService;

  @Value("${spring.web.resources.static-locations}")
  private String uploadPath;

    private final ResourceLoader resourceLoader;


  @GetMapping("/init")
  public ResponseEntity<InitDto> initSpaceEntity(
    @RequestHeader(value = "authorization", required = true) String  accessToken) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);
      InitDto initDto = initService.currentInit(user);

      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @PostMapping("/page/create")
  public ResponseEntity<InitDto> createNewPage(
    @RequestBody CreatePageDto createPageDto, 
    @RequestHeader(value = "authorization", required = true) String  accessToken) {
    Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String email = userDetails.getUsername();
    UserModel user = authService.findUserByEmail(email);

    PersonalspaceModel personalspaceModel = spaceService.findPersonalspaceByWorkspaceUuid(createPageDto.getWorkspaceUuid());
    TeamspaceModel teamspaceModel = new TeamspaceModel();
    PageModel pageModel = new PageModel();


      // if(createPageDto.getPersonalspaceUuid() != null)
      //   {personalspaceModel = spaceService.findPersonalspaceByUuid(createPageDto.getPersonalspaceUuid());}
      //   else {personalspaceModel = null;}
      // if(createPageDto.getTeamspaceUuid() != null){
      //   teamspaceModel = spaceService.findTeamspaceByUuid(createPageDto.getTeamspaceUuid());
      // }
      // else {
        teamspaceModel = null;
      // }
      // if(createPageDto.getParentPageUuid() != null){
      //   pageModel = spaceService.findPageByUuid(createPageDto.getParentPageUuid());
      // }
      // else{
        pageModel = null;
      // }

      PageModel newPageModel = initModelService.pageInit(personalspaceModel, teamspaceModel, pageModel);
      initModelService.blockInit(newPageModel, 1, "text");
      CurrentModel currentModel = spaceService.findCurrentByUserUuid(user.getUuid());
      WorkspaceModel workspaceModel = spaceService.findWorkspaceByWorkspaceUuid(createPageDto.getWorkspaceUuid());
      spaceService.updateCurrent(currentModel, newPageModel, workspaceModel);

      InitDto initDto = initService.currentInit(user);

      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @PostMapping("/page/select")
  public ResponseEntity<InitDto> selectPage(@RequestBody SelectPageDto selectPageDto, 
    @RequestHeader(value = "authorization", required = true) String  accessToken) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);

      CurrentModel currentModel = spaceService.findCurrentByUserUuid(user.getUuid());
      WorkspaceModel workspaceModel = spaceService.findWorkspaceByWorkspaceUuid(selectPageDto.getWorkspaceUuid());
      PageModel pageModel = spaceService.findPageByUuid(selectPageDto.getPageUuid());
      spaceService.updateCurrent(currentModel, pageModel, workspaceModel);

      InitDto initDto = initService.currentInit(user);
      

      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @PostMapping("/page/update")
  public ResponseEntity<InitDto> updatePage(
    @RequestBody UpdateDto updateDto, 
    @RequestHeader(value = "authorization", required = true) String  accessToken) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);

      PageModel pageModel = spaceService.findPageByUuid(updateDto.getPageUuid());
      spaceService.updatePage(pageModel, updateDto.getTitle(), updateDto.getText());

      InitDto initDto = initService.currentInit(user);
      

      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @PostMapping("/page/block/update")
  public ResponseEntity<InitDto> updateBlock (
    @RequestBody UpdateBlockDto updateBlockDto, 
    @RequestHeader(value = "authorization", required = true) String  accessToken
  ) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);

      BlockModel blockModel = spaceService.findBlockByUuid(updateBlockDto.getBlockUuid());
      spaceService.updateBlock(blockModel, updateBlockDto.getType(), updateBlockDto.getData());

      InitDto initDto = initService.currentInit(user);
      
      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @PostMapping("/page/block/create")
  public ResponseEntity<InitDto> createBlock(@RequestBody UpdateBlockDto createBlockDto, 
    @RequestHeader(value = "authorization", required = true) String accessToken) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);

      BlockModel blockModel = spaceService.findBlockByUuid(createBlockDto.getBlockUuid());
      initModelService.blockInit(blockModel.getPage(), createBlockDto.getCount(), createBlockDto.getType());

      
      InitDto initDto = initService.currentInit(user);
      
      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @PostMapping("/image")
  public ResponseEntity<InitDto> createImage(
    @RequestParam("file") MultipartFile file,
    @RequestHeader(value = "authorization", required = true) String accessToken,
    @RequestHeader(value = "uuid", required = true) String uuid
  ) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);

      try{
        
            Path rootLocation = Paths.get(uploadPath);
            Path destinationFile = rootLocation.resolve(
                    Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            file.transferTo(destinationFile);
            String imageUrl = "/assets/images/" + file.getOriginalFilename();
            
      String data = imageUrl;
      BlockModel blockModel = spaceService.findBlockByUuid(uuid);
      spaceService.updateBlock(blockModel, "image", data);
      }
      catch(IOException e){
        // log.info("////////// image : " + e.toString());
      }
    
      InitDto initDto = initService.currentInit(user);
      
      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

  @GetMapping("/assets/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
       try {
        // 실제 파일이 저장되는 경로, 예를 들어 외부 경로나 설정된 디렉토리를 사용
        Path file = Paths.get(uploadPath).resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // MIME 타입은 파일 유형에 따라 변경 가능
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (MalformedURLException e) {
        return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/page/block/delete")
  public ResponseEntity<InitDto> deleteBlock(@RequestBody DeleteBlockDto deleteBlockDto, 
    @RequestHeader(value = "authorization", required = true) String accessToken
    ) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);
      BlockModel blockModel = spaceService.findBlockByUuid(deleteBlockDto.getBlockUuid());
      spaceService.deleteBlock(blockModel);

      
      InitDto initDto = initService.currentInit(user);
      
      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }

}
