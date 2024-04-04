import styled from "@emotion/styled";
import { Box } from "@mui/material";

const HeaderSection = styled(Box)({
  height: "10vh",
  display: "flex",
  paddingLeft: "15vw",
  alignItems: "center",
});
const HeaderTitleBox = styled(Box)({
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  fontSize: "40px",
});
const SeparateColumnLine = styled(Box)({
  height: "2vh",
  width: "1px",
  margin: "20px",
  backgroundColor: "#ddd",
});

export { HeaderSection, HeaderTitleBox, SeparateColumnLine };
