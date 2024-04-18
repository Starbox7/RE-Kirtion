import styled from "@emotion/styled";
import { Box, Button, TextField, Link } from "@mui/material";
import ReactTextareaAutosize from "react-textarea-autosize";

const SpaceBox = styled(Box)({
  display: "flex",
});

const PageBox = styled(Box)(({ state }) => ({
  display: "flex",
  flexDirection: "column",
  marginLeft: state ? "245px" : "0px",
}));

const PageContentBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  marginTop: "45px",
});

const Img = styled.img`
  width: ${({ state }) => (state ? "calc(100vw - 245px)" : "100vw")};
  height: 350px;
  object-fit: cover;
`;
const TitleTextarea = styled.textarea`
  width: ${({ state }) =>
    state ? "calc(100vw - 445px)" : "calc(100vw-200px)"};
  border: none;
  margin: 0 100px;
  margin-top: 40px;

  font-size: 50px;
  font-weight: bold;
  resize: none;
  overflow-y: hidden;
  &:focus {
    outline: none;
  }
  &::placeholder {
    color: #ddd;
  }
`;

const BlockBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
});

const ContentAutoArea = styled(ReactTextareaAutosize)`
  border: none;
  padding: 0 100px;

  &:focus {
    outline: none;
  }
  &:placeholder {
    color: #ddd;
  }
`;

const IconBox = styled(Box)(({ state }) => ({
  position: "absolute",
  top: "290px" /* 커버 이미지 영역의 바닥에 위치 */,
  left: state ? "370px" : "130px" /* 가운데 정렬 */,
  transform: "translate(-25%, 50%)" /* 아이콘 이미지를 정확히 중앙에 위치 */,
  zIndex: 2 /* 페이지 컨텐츠 위에 표시되도록 */,
  /* 아이콘 스타일 */
}));

export {
  SpaceBox,
  PageBox,
  TitleTextarea,
  ContentAutoArea,
  Img,
  IconBox,
  PageContentBox,
  BlockBox,
};
