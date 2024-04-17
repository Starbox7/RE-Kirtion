import { faEllipsis } from "@fortawesome/free-solid-svg-icons";
import {
  RoutingBarBox,
  RouteSet,
  RouteBarOptionButtonSet,
  ShareButton,
  RouteBarOptionBox,
} from "./routingbar.style";

import {
  faClock,
  faMessage,
  faStar,
} from "@fortawesome/free-regular-svg-icons";

export default function Routingbar() {
  return (
    <RoutingBarBox>
      <RouteSet
        image="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567008788/noticon/bqjhb6xvljt9viccy6lh.png"
        text="Kirtion Title"
      />
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
