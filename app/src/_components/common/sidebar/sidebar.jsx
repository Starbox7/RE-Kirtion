import { useEffect, useState } from "react";
import {
  SidebarBox,
  WorkspaceBox,
  WorkspaceTitleTextBox,
  WorkspaceTitleBox,
  WorkspaceLogoImg,
  WorkspaceTitleText,
  UserEmailText,
  UpIcon,
  DownIcon,
  IconMergeBox,
  SidebarOnOffButton,
  LeftIcon,
  FunctionSet,
  MarginBox,
  SpaceAddBox,
  SpaceAddButton,
  SpaceButton,
  SpaceAddIcon,
  TeamspcaeTitlText,
  TeamspaceLogoImg,
  PageSet,
  TeamspaceTitleBox,
} from "./sidebar.style";
import {
  faMagnifyingGlass,
  faInbox,
  faGear,
  faCirclePlus,
  faCalendarCheck,
  faWindowRestore,
  faDownload,
  faTrashCan,
  faRectangleList,
} from "@fortawesome/free-solid-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import {
  setSidebarState,
  setUpdateBlockState,
  setUpdateState,
} from "../../../features/state.slice";
import { useMutation } from "@tanstack/react-query";
import spaceRepo from "../../../repositories/space.repository";
import useAuthorize from "../hooks/useAuthorize";
import {
  setCurrentPage,
  setCurrentWorkspace,
  // setPersonalspacePageList,
  setPageListInTeamspaceList,
  setBlockListInPersonalPageList,
  setCurrentPageBlockList,
} from "../../../features/space.slice";

export default function Sidebar() {
  const [isMouseOn, setIsMouseOn] = useState(false);
  const currentWorkspaceData = useSelector(
    (state) => state.space.currentWorkspace
  );
  // const personalPageList = useSelector(
  //   (state) => state.space.personalspacePageList
  // );
  const blockListInPersonalPageList = useSelector(
    (state) => state.space.blockListInPersonalPageList
  );
  const sidebarState = useSelector((state) => state.state.sidebarState);
  const dispatch = useDispatch();
  const { getAccessToken } = useAuthorize();
  const mutation = useMutation({ mutationFn: spaceRepo.createNewPage });
  const selectPageMutation = useMutation({ mutationFn: spaceRepo.selectPage });

  return (
    <SidebarBox
      onMouseEnter={() => setIsMouseOn(true)}
      onMouseLeave={() => setIsMouseOn(false)}
      state={sidebarState}
    >
      <WorkspaceBox>
        <WorkspaceTitleBox>
          <WorkspaceLogoImg src={currentWorkspaceData.logo} />
          <WorkspaceTitleTextBox>
            <WorkspaceTitleText>{currentWorkspaceData.name}</WorkspaceTitleText>
            <UserEmailText>{currentWorkspaceData.plan}</UserEmailText>
          </WorkspaceTitleTextBox>
          <IconMergeBox>
            <UpIcon />
            <DownIcon />
          </IconMergeBox>
          <SidebarOnOffButton
            onClick={() => {
              dispatch(setSidebarState(false));
            }}
            isMouseOn={isMouseOn}
          >
            <LeftIcon />
          </SidebarOnOffButton>
        </WorkspaceTitleBox>
        <FunctionSet icon={faMagnifyingGlass} text="Search" />
        <FunctionSet icon={faInbox} text="Inbox" />
        <FunctionSet icon={faGear} text="Settings & Members" />
        <FunctionSet
          onClick={async () => {
            const accessToken = getAccessToken();
            const uuid = currentWorkspaceData.uuid;
            await mutation.mutateAsync({ accessToken, uuid }).then((result) => {
              dispatch(setCurrentPage(result.data.current_page));
              dispatch(
                // setPersonalspacePageList(result.data.personal_page_list)
                setBlockListInPersonalPageList(
                  result.data.block_list_in_personal_page_list
                )
              );
            });
          }}
          icon={faCirclePlus}
          text="New Page"
        />
      </WorkspaceBox>
      <MarginBox />
      <WorkspaceBox>
        <SpaceAddBox>
          <SpaceButton>Team space</SpaceButton>
          <SpaceAddButton isMouseOn={isMouseOn}>
            <SpaceAddIcon />
          </SpaceAddButton>
        </SpaceAddBox>
        <TeamspaceTitleBox>
          <TeamspaceLogoImg src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1611643708/noticon/cvs54o0f0wblhhepnaeq.png" />
          <TeamspcaeTitlText>Team 1</TeamspcaeTitlText>
        </TeamspaceTitleBox>
        <PageSet
          image="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1575060109/noticon/kkzcn5hjh5f6lq8hxea4.svg"
          text="Page"
        />
      </WorkspaceBox>
      <MarginBox />
      <WorkspaceBox>
        <SpaceAddBox>
          <SpaceButton>Personal Page</SpaceButton>
          <SpaceAddButton isMouseOn={isMouseOn}>
            <SpaceAddIcon />
          </SpaceAddButton>
        </SpaceAddBox>
        {blockListInPersonalPageList.map((page) => (
          <PageSet
            image={page.personalPage.icon}
            text={page.personalPage.title}
            onClick={async () => {
              const accessToken = getAccessToken();
              const pageUuid = page.personalPage.uuid;
              const workspaceUuid = currentWorkspaceData.uuid;
              await selectPageMutation
                .mutateAsync({
                  accessToken,
                  pageUuid,
                  workspaceUuid,
                })
                .then((result) => {
                  dispatch(setUpdateState(true));
                  dispatch(setUpdateBlockState(true));
                  dispatch(setCurrentPage(result.data.current_page));
                  dispatch(
                    setCurrentPageBlockList(result.data.current_page_block_list)
                  );
                  // dispatch(
                  //   setCurrentPageBlockList(result.data.current_page_block_list)
                  // );
                });
            }}
          />
        ))}
      </WorkspaceBox>
      <MarginBox />
      <WorkspaceBox>
        <FunctionSet icon={faCalendarCheck} text="Calendar" />
        <FunctionSet icon={faWindowRestore} text="Create Teamspace" />
        <FunctionSet icon={faRectangleList} text="Templates" />
        <FunctionSet icon={faDownload} text="Import" />
        <FunctionSet icon={faTrashCan} text="Recycle Bin" />
      </WorkspaceBox>
    </SidebarBox>
  );
}
