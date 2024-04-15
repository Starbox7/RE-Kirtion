import { useQuery } from "@tanstack/react-query";
import spaceRepo from "../../repositories/space.repository";
import useHome from "./useHome";
import Logo from "../common/kirby/kirby";
import {
  ContentBox,
  CoverBox,
  PageBox,
  RoutingBarBox,
  SideBarBox,
  SpaceBox,
  TitleBox,
  IconBox,
} from "./home.style";

export default function Home() {
  const { isLoading, isError, data, error } = useQuery({
    queryKey: ["spaceInit"],
    queryFn: () => spaceRepo.spaceInit(getAccessToken()),
  });
  const { getAccessToken } = useHome();

  return (
    <SpaceBox>
      <SideBarBox>test</SideBarBox>
      <PageBox>
        <RoutingBarBox>rbb</RoutingBarBox>
        <CoverBox>cb</CoverBox>
        <IconBox>
          <Logo />
        </IconBox>
        <TitleBox>tb</TitleBox>
        <ContentBox>cb</ContentBox>
      </PageBox>
    </SpaceBox>
  );
}
