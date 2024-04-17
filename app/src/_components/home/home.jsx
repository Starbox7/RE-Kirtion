import { useQuery } from "@tanstack/react-query";
import spaceRepo from "../../repositories/space.repository";
import useHome from "./useHome";
import { Lorem, TestPageText } from "../../constants/constant";
import {
  ContentBox,
  PageBox,
  SpaceBox,
  TitleBox,
  IconBox,
  PageContentBox,
  BlockBox,
  Img,
} from "./home.style";
import Sidebar from "../common/sidebar/sidebar";
import Routingbar from "../common/routingbar/routingbar";

export default function Home() {
  const { isLoading, isError, data, error } = useQuery({
    queryKey: ["spaceInit"],
    queryFn: () => spaceRepo.spaceInit(getAccessToken()),
  });
  const { getAccessToken } = useHome();

  return (
    <SpaceBox>
      <Sidebar />
      <PageBox>
        <Routingbar />
        <PageContentBox>
          <Img src="https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=3200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" />
          <IconBox>
            <img
              style={{ width: "90px" }}
              src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1567008788/noticon/bqjhb6xvljt9viccy6lh.png"
              alt=""
            />
          </IconBox>
          <TitleBox>Kirtion Title</TitleBox>
          <BlockBox>
            <ContentBox>{TestPageText()}</ContentBox>
          </BlockBox>
        </PageContentBox>
      </PageBox>
    </SpaceBox>
  );
}
