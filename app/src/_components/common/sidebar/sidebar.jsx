import { useState } from "react";
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

export default function Sidebar() {
  const [isMouseOn, setIsMouseOn] = useState(false);
  return (
    <SidebarBox
      onMouseEnter={() => setIsMouseOn(true)}
      onMouseLeave={() => setIsMouseOn(false)}
    >
      <WorkspaceBox>
        <WorkspaceTitleBox>
          <WorkspaceLogoImg src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1673081460/noticon/iqrrp7ziu0xuvltesgbs.png" />
          <WorkspaceTitleTextBox>
            <WorkspaceTitleText>Arcione07's Kirtion</WorkspaceTitleText>
            <UserEmailText>arcione07@naver.com</UserEmailText>
          </WorkspaceTitleTextBox>
          <IconMergeBox>
            <UpIcon />
            <DownIcon />
          </IconMergeBox>
          <SidebarOnOffButton isMouseOn={isMouseOn}>
            <LeftIcon />
          </SidebarOnOffButton>
        </WorkspaceTitleBox>
        <FunctionSet icon={faMagnifyingGlass} text="Search" />
        <FunctionSet icon={faInbox} text="Inbox" />
        <FunctionSet icon={faGear} text="Settings & Members" />
        <FunctionSet icon={faCirclePlus} text="New Page" />
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
        <PageSet
          image="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1575060109/noticon/kkzcn5hjh5f6lq8hxea4.svg"
          text="Page"
        />
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
