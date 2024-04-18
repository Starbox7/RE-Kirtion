import styled from "@emotion/styled";
import { Box, Button } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

const RoutingBarBox = styled(Box)(({ state }) => ({
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  width: state ? "calc(100vw - 245px)" : "100vw",
  height: "45px",
  minHeight: "45px",
  position: "fixed",
  background: "white",
  zIndex: 3,
}));
const RouteBox = styled(Box)({
  display: "flex",
  justifyContent: "flex-start",
  alignItems: "center",
  width: "100px",
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
const SidebarOnOffButton = styled(Button)(({}) => ({
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
function BarIcon() {
  return (
    <FontAwesomeIcon
      icon={faBars}
      style={{
        color: "gray",
        margin: 0,
        padding: 0,
      }}
    />
  );
}
function RouteSet({ image, text }) {
  return (
    <RouteBox>
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
          color: "black",
          fontSize: "14px",
          marginLeft: "5px",
          overflow: "hidden",
          textOverflow: "ellipsis",
          whiteSpace: "nowrap",
        }}
      >
        {text}
      </p>
    </RouteBox>
  );
}
const RouteBarOptionBox = styled(Box)({
  display: "flex",
  marginRight: "15px",
});
const ShareButton = styled(Button)({
  textTransform: "none",
  color: "black",
  height: "30px",
});
function RouteBarOptionButtonSet({ icon }) {
  return (
    <RouteBarOptionButton>
      <FontAwesomeIcon
        icon={icon}
        style={{
          color: "#4c4c4c",
        }}
      />
    </RouteBarOptionButton>
  );
}
const RouteBarOptionButton = styled(Button)({
  display: "flex",
  justifyContent: "center",
  width: "31px",
  minWidth: "31px",
  height: "31px",
});

export {
  RoutingBarBox,
  RouteBox,
  RouteBarOptionBox,
  ShareButton,
  RouteSet,
  RouteBarOptionButtonSet,
  SidebarOnOffButton,
  BarIcon,
};
