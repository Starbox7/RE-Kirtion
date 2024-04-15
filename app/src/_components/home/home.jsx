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
  PageContentBox,
  BlockBox,
} from "./home.style";

const generateLongText = () => {
  let longText = "";
  for (let i = 0; i < 1000; i++) {
    longText += "가"; // 긴 텍스트를 생성하기 위해 한글자씩 추가합니다.
  }
  return longText;
};

const longText = generateLongText();

export default function Home() {
  const { isLoading, isError, data, error } = useQuery({
    queryKey: ["spaceInit"],
    queryFn: () => spaceRepo.spaceInit(getAccessToken()),
  });
  const { getAccessToken } = useHome();

  return (
    <SpaceBox>
      <SideBarBox></SideBarBox>
      <PageBox>
        <RoutingBarBox></RoutingBarBox>
        <PageContentBox>
          {/* <IconBox>
            <img src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1570106347/noticon/hx52ypkqqdzjdvd8iaid.svg" />
          </IconBox> */}
          <CoverBox></CoverBox>
          <TitleBox></TitleBox>
          <BlockBox>
            <ContentBox>{longText}</ContentBox>
            <ContentBox>{longText}</ContentBox>
            <ContentBox>{longText}</ContentBox>
          </BlockBox>
        </PageContentBox>
      </PageBox>
    </SpaceBox>
  );
}
