import styled from "@emotion/styled";
import { Box, Button, TextField, Link } from "@mui/material";

const SpaceBox = styled(Box)({
  display: "flex",
});

const PageBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  marginLeft: "245px",
});

const PageContentBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  marginTop: "45px",
});

const Img = styled.img`
  width: calc(100vw - 245px);
  height: 350px;
  object-fit: cover;
`;
const TitleBox = styled(Box)({
  display: "flex",
  height: "40px",
  minHeight: "40px",
  padding: "3px",
  paddingTop: "70px",
  paddingBottom: "20px",
  marginLeft: "100px",
  marginRight: "100px",

  fontSize: "50px",
  fontWeight: "bold",
});
const BlockBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
});

const ContentBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  flex: 1,
  marginLeft: "100px",
  marginRight: "100px",
});

const IconBox = styled(Box)({
  position: "absolute",
  top: "290px" /* 커버 이미지 영역의 바닥에 위치 */,
  left: "340px" /* 가운데 정렬 */,
  transform: "translate(-25%, 50%)" /* 아이콘 이미지를 정확히 중앙에 위치 */,
  zIndex: 2 /* 페이지 컨텐츠 위에 표시되도록 */,
  /* 아이콘 스타일 */
});

export {
  SpaceBox,
  PageBox,
  TitleBox,
  ContentBox,
  Img,
  IconBox,
  PageContentBox,
  BlockBox,
};
