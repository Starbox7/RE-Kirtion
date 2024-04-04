import styled from "@emotion/styled";
import { Box, Button, TextField } from "@mui/material";

const AuthBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  width: "100vw",
});
const ContentBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
  height: "60vh",
  marginBottom: "20vh",
  "@media (max-width: 600px)": {
    height: "80vh",
    marginBottom: "5vh",
  },
});
const TitleBox = styled(Box)({
  fontSize: "50px",
  marginBottom: "20px",
});
const AuthTextField = styled(TextField)({
  margin: "10px",
  width: "370px",
});
const DescriptionBox = styled(Box)({
  color: "lightgray",
  fontSize: "14px",
});
const ContinueButton = styled(Button)({
  width: "370px",
  marginTop: "20px",
});
const SeparateRowLineBox = styled(Box)({
  backgroundColor: "#ddd",
  width: "370px",
  height: "1px",
  margin: "20px",
});
const SocialButton = styled(Button)({
  fontSize: "15px",
  paddingLeft: "70px",
  paddingRight: "70px",
  color: "black",
  textTransform: "none",
  width: "370px",
  margin: "10px",
  borderColor: "lightgray",
});
const Img = styled("img")({
  height: "20px",
  marginRight: "10px",
});
const HelpBox = styled(Box)({
  display: "flex",
});
const HelpTextBox = styled(Box)({
  color: "gray",
  marginRight: "5px",
});
const ValidBox = styled(Box)({
  color: "red",
  fontSize: "14px",
  display: "flex",
  alignItems: "center",
  width: "370px",
});

export {
  AuthBox,
  AuthTextField,
  ContentBox,
  ContinueButton,
  DescriptionBox,
  HelpBox,
  HelpTextBox,
  Img,
  SeparateRowLineBox,
  SocialButton,
  TitleBox,
  ValidBox,
};
