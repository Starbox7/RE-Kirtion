import { faEllipsis } from "@fortawesome/free-solid-svg-icons";
import {
  RoutingBarBox,
  RouteSet,
  RouteBarOptionButtonSet,
  ShareButton,
  RouteBarOptionBox,
  SidebarOnOffButton,
  BarIcon,
} from "./routingbar.style";

import {
  faClock,
  faMessage,
  faStar,
} from "@fortawesome/free-regular-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import { Box } from "@mui/material";
import { setSidebarState } from "../../../features/state.slice";

export default function Routingbar() {
  const currentPage = useSelector((state) => state.space.currentPage);
  const sidebarState = useSelector((state) => state.state.sidebarState);
  const dispatch = useDispatch();
  return (
    <RoutingBarBox state={sidebarState}>
      <Box sx={{ display: "flex" }}>
        {!sidebarState ? (
          <SidebarOnOffButton
            onClick={() => {
              dispatch(setSidebarState(true));
            }}
          >
            <BarIcon />
          </SidebarOnOffButton>
        ) : null}
        <RouteSet image={currentPage.icon} text={currentPage.title} />
      </Box>
      <RouteBarOptionBox>
        <ShareButton>Share</ShareButton>
        <RouteBarOptionButtonSet icon={faMessage} />
        <RouteBarOptionButtonSet icon={faClock} />
        <RouteBarOptionButtonSet icon={faStar} />
        <RouteBarOptionButtonSet icon={faEllipsis} />
      </RouteBarOptionBox>
    </RoutingBarBox>
  );
}
