import styled from "@emotion/styled";
import {
  faAngleRight,
  faAnglesLeft,
  faChevronDown,
  faChevronUp,
  faEllipsis,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Box, Button } from "@mui/material";
import { useState } from "react";

const SidebarBox = styled(Box)(({ state }) => ({
  display: "flex",
  flexDirection: "column",
  alignItems: "flex-start",
  width: "245px",
  minWidth: "245px",
  height: "100vh",
  background: "#F7F7F5",
  position: "fixed",

  transform: `translateX(${state}? "0" : "-100%")`,
  translation: "transform 1.0s ease-out",
}));

const WorkspaceBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
});
const WorkspaceTitleBox = styled(Box)({
  display: "flex",
  justifyContent: "flex-start",
  alignItems: "center",
  width: "220px",
  height: "20px",
  margin: "4px",
  marginBottom: "5px",
  padding: "8px",
  textTransform: "none",
  borderRadius: "5px",
  "&:hover": {
    backgroundColor: "#E8E8E5",
  },
});
const WorkspaceLogoImg = styled.img`
  width: 23px;
  margin: 5px;
  margin-right: 7px;
  border-radius: 5px;
`;
const WorkspaceTitleTextBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  height: "27px",
});
const WorkspaceTitleText = styled.p`
  text-align: start;
  width: 125px;
  font-size: 12px;
  color: black;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: bold;
  margin: 0;
`;
const UserEmailText = styled.p`
  text-align: start;
  width: 125px;
  font-size: 11px;
  color: gray;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin: 0;
`;
const IconMergeBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  marginLeft: "15px",
  marginBottom: "7px",
});
function UpIcon() {
  return (
    <FontAwesomeIcon
      icon={faChevronUp}
      style={{
        color: "gray",
        width: "8px",
        height: "6px",
        margin: 0,
        padding: 0,
      }}
    />
  );
}
function DownIcon() {
  return (
    <FontAwesomeIcon
      icon={faChevronDown}
      style={{
        color: "gray",
        width: "8px",
        height: "6px",
        margin: 0,
        padding: 0,
      }}
    />
  );
}
const SidebarOnOffButton = styled(Button)(({ isMouseOn }) => ({
  opacity: isMouseOn ? 1 : 0,
  visibility: isMouseOn ? "visible" : "hidden",
  transition: "opacity 0.3s ease, visibility 0.3s ease",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  height: "25px",
  width: "20px",
  minWidth: "15px",
  marginLeft: "20px",
  paddingLeft: "5px",
  paddingRight: "5px",
  marginBottom: "5px",
  "&:hover": {
    backgroundColor: "lightgray",
  },
}));
function LeftIcon() {
  return (
    <FontAwesomeIcon
      icon={faAnglesLeft}
      style={{
        color: "gray",
        margin: 0,
        padding: 0,
      }}
    />
  );
}
const FunctionBox = styled(Box)({
  display: "flex",
  justifyContent: "flex-start",
  alignItems: "center",
  width: "220px",
  height: "15px",
  margin: "4px",
  marginTop: "0px",
  marginBottom: "0px",
  padding: "6px",
  textTransform: "none",
  borderRadius: "5px",
  "&:hover": {
    backgroundColor: "#E8E8E5",
  },
});
function FunctionSet({ icon, text, onClick }) {
  return (
    <FunctionBox onClick={onClick}>
      <FontAwesomeIcon
        icon={icon}
        style={{
          color: "gray",
          width: "13px",
          marginLeft: "15px",
        }}
      />
      <p style={{ color: "gray", fontSize: "15px", marginLeft: "10px" }}>
        {text}
      </p>
    </FunctionBox>
  );
}
const SpaceAddBox = styled(Box)({
  display: "flex",
  justifyContent: "space-between",
  width: "220px",
  marginLeft: "12px",
});
const SpaceButton = styled(Button)({
  height: "20px",
  minHeight: "20px",
  color: "gray",
  fontSize: "11px",
  textTransform: "none",
  "&:hover": {
    backgroundColor: "#E8E8E5",
  },
});
const SpaceAddButton = styled(Button)(({ isMouseOn }) => ({
  opacity: isMouseOn ? 1 : 0,
  visibility: isMouseOn ? "visible" : "hidden",
  transition: "opacity 0.3s ease, visibility 0.3s ease",
  width: "20px",
  minWidth: "20px",
  height: "20px",
  minHeight: "20px",
  "&:hover": {
    backgroundColor: "lightgray",
  },
}));
function SpaceAddIcon() {
  return (
    <FontAwesomeIcon
      icon={faPlus}
      style={{
        color: "gray",
        width: "11px",
        margin: 0,
        padding: 0,
      }}
    />
  );
}
const TeamspaceTitleBox = styled(Box)({
  display: "flex",
  justifyContent: "flex-start",
  alignItems: "center",
  width: "220px",
  height: "15px",
  margin: "4px",
  marginTop: "0px",
  marginBottom: "0px",
  padding: "8px",
  textTransform: "none",
  borderRadius: "5px",
  "&:hover": {
    backgroundColor: "#E8E8E5",
  },
});
const TeamspaceLogoImg = styled.img`
  width: 19px;
  margin: 5px;
  margin-left: 11px;
  margin-right: 7px;
  border-radius: 5px;
`;
const TeamspcaeTitlText = styled.p`
  text-align: start;
  width: 125px;
  font-size: 12px;
  color: #4c4c4c;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: bold;
  margin: 0;
`;

function PageSet({ image, text, onClick }) {
  const [isMouseOnButton, setIsMouseOnButton] = useState(false);
  return (
    <FunctionBox
      onMouseEnter={() => setIsMouseOnButton(true)}
      onMouseLeave={() => setIsMouseOnButton(false)}
      onClick={onClick}
    >
      <Box
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          width: "220px",
        }}
      >
        <Box style={{ display: "flex", alignItems: "center" }}>
          <FontAwesomeIcon
            icon={faAngleRight}
            style={{
              color: "lightgray",
              width: "8px",
              marginLeft: "15px",
            }}
          />
          <img
            src={image}
            style={{
              color: "gray",
              width: "15px",
              marginLeft: "5px",
            }}
            alt=""
          />
          <p
            style={{
              width: "120px",
              color: "#606060",
              fontSize: "14px",
              marginLeft: "5px",
              overflow: "hidden",
              textOverflow: "ellipsis",
            }}
          >
            {text}
          </p>
        </Box>
        <Box style={{ display: "flex" }}>
          <PageEllipsisButton isMouseOn={isMouseOnButton}>
            <PageEllipsisIcon />
          </PageEllipsisButton>
          <SpaceAddButton isMouseOn={isMouseOnButton}>
            <SpaceAddIcon />
          </SpaceAddButton>
        </Box>
      </Box>
    </FunctionBox>
  );
}
const PageEllipsisButton = styled(Button)(({ isMouseOn }) => ({
  opacity: isMouseOn ? 1 : 0,
  visibility: isMouseOn ? "visible" : "hidden",
  transition: "opacity 0.3s ease, visibility 0.3s ease",
  width: "20px",
  minWidth: "20px",
  height: "20px",
  minHeight: "20px",
  "&:hover": {
    backgroundColor: "lightgray",
  },
}));
function PageEllipsisIcon() {
  return (
    <FontAwesomeIcon
      icon={faEllipsis}
      style={{
        color: "gray",
        width: "11px",
        margin: 0,
        padding: 0,
      }}
    />
  );
}

const MarginBox = styled(Box)({
  marginTop: "12px",
  marginBottom: "12px",
});

export {
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
};
