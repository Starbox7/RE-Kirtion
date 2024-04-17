import styled from "@emotion/styled";
import { Box, Button } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const RoutingBarBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  justifyContent: "space-between",
  width: "calc(100vw - 245px)",
  height: "45px",
  minHeight: "45px",
  position: "fixed",
  background: "white",
  zIndex: 3,
});
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
function RouteSet({ image, text }) {
  // const [isMouseOnButton, setIsMouseOnButton] = useState(false);
  return (
    <RouteBox
    // onMouseEnter={() => setIsMouseOnButton(true)}
    // onMouseLeave={() => setIsMouseOnButton(false)}
    >
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
          fontWeight: "bold",
        }}
      />
    </RouteBarOptionButton>
  );
}
const RouteBarOptionButton = styled(Button)({
  display: "flex",
  justifyContent: "center",
  width: "30px",
  minWidth: "30px",
  height: "30px",
});

export {
  RoutingBarBox,
  RouteBox,
  RouteBarOptionBox,
  ShareButton,
  RouteSet,
  RouteBarOptionButtonSet,
};
