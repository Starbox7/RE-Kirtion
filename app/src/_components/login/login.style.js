import styled from "@emotion/styled";
import { Box, Button, TextField } from "@mui/material";

const AuthSection = styled(Box)({
  display: "flex",
  flexDirection: "column",
  width: "100vw",
});
const Content = styled(Box)({
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
const Title = styled(Box)({
  fontSize: "50px",
  marginBottom: "20px",
});
const Input = styled(TextField)({
  margin: "10px",
  width: "370px",
});
const Description = styled(Box)({
  color: "lightgray",
  fontSize: "14px",
});
const Signin = styled(Button)({
  width: "370px",
  marginTop: "20px",
});
const SeparateRowLine = styled(Box)({
  backgroundColor: "#ddd",
  width: "370px",
  height: "1px",
  margin: "20px",
});
const Social = styled(Button)({
  fontSize: "15px",
  paddingLeft: "70px",
  paddingRight: "70px",
  color: "black",
  textTransform: "none",
  width: "370px",
  margin: "10px",
  borderColor: "lightgray",
});
const Image = styled("img")({
  height: "20px",
  marginRight: "10px",
});
const Help = styled(Box)({
  display: "flex",
});
const HelpText = styled(Box)({
  color: "gray",
  marginRight: "5px",
});

export {
  AuthSection,
  Content,
  Title,
  Input,
  Description,
  Signin,
  SeparateRowLine,
  Social,
  Image,
  Help,
  HelpText,
};
