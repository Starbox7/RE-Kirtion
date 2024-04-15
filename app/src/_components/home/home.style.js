import styled from "@emotion/styled";
import { Box, Button, TextField, Link } from "@mui/material";

const SpaceBox = styled(Box)({
  // display: "flex",
  // width: "100vw",
  // height: "100vh",
});

const SideBarBox = styled(Box)({
  display: "flex",
  width: "270px",
  height: "100%",
  background: "lightgray",
});

const PageBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  width: "100%",
  height: "100%",
  background: "white",
});

const RoutingBarBox = styled(Box)({
  display: "flex",
  height: "45px",
  background: "white",
});
const RouteBox = styled(Box)({});
const RouteBarOptionBox = styled(Box)({});
const ShareBox = styled(Box)({});

const PageContentBox = styled(Box)({
  display: "flex",
  height: "100%",
  flexDirection: "column",
  // overflowY: "auto",
});

const CoverBox = styled(Box)({
  display: "flex",
  height: "290px",
  background: "lightblue",
  // overflowY: "hidden",
});
const IconBox = styled(Box)({
  position: "absolute",
  top: "24%" /* 커버 이미지 영역의 바닥에 위치 */,
  left: "17%" /* 가운데 정렬 */,
  transform: "translate(-50%, -50%)" /* 아이콘 이미지를 정확히 중앙에 위치 */,
  zIndex: 2 /* 페이지 컨텐츠 위에 표시되도록 */,
  /* 아이콘 스타일 */
});
const TitleBox = styled(Box)({
  display: "flex",
  height: "160px",
  border: "1px solid red",
  // position: "relative",
  // zIndex: 1,
  marginLeft: "100px",
  marginRight: "100px",
});
const BlockBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  height: "100%",
  // overflow: "hidden",
  // overflowY: "auto",
});

const ContentBox = styled(Box)({
  display: "flex",
  // flex: 1,
  border: "1px solid blue",
  marginLeft: "100px",
  marginRight: "100px",
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
  PageContentBox,
  BlockBox,
};
