import styled from "@emotion/styled";
import { Box, Button, TextField, Link } from "@mui/material";

const SpaceBox = styled(Box)({
  display: "flex",
  width: "100vw",
  height: "100vh",
  justifyContent: "flex-start",
});

const SideBarBox = styled(Box)({
  display: "flex",
  width: "265px",
  background: "lightgray",
});

const PageBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  width: "100%",
  background: "gray",
});

const RoutingBarBox = styled(Box)({
  display: "flex",
  height: "45px",
  background: "red",
});
const RouteBox = styled(Box)({});
const RouteBarOptionBox = styled(Box)({});
const ShareBox = styled(Box)({});

const CoverBox = styled(Box)({
  display: "flex",
  height: "30%",
  background: "#ddd",
  position: "relative",
  zIndex: 1,
});
const IconBox = styled(Box)({
  position: "absolute",
  top: "100%" /* 커버 이미지 영역의 바닥에 위치 */,
  left: "50%" /* 가운데 정렬 */,
  transform: "translate(-50%, -50%)" /* 아이콘 이미지를 정확히 중앙에 위치 */,
  zIndex: 2 /* 페이지 컨텐츠 위에 표시되도록 */,
  /* 아이콘 스타일 */
});
const TitleBox = styled(Box)({
  display: "flex",
  height: "130px",
  background: "green",
  position: "relative",
  zIndex: 1,
});

const ContentBox = styled(Box)({
  display: "flex",
  height: "100%",
  background: "yellow",
  position: "relative",
  zIndex: 1,
});

export {
  SpaceBox,
  SideBarBox,
  PageBox,
  RoutingBarBox,
  RouteBox,
  RouteBarOptionBox,
  ShareBox,
  TitleBox,
  ContentBox,
  CoverBox,
  IconBox,
};
