import {
  ContentAutoArea,
  PageBox,
  SpaceBox,
  TitleTextarea,
  IconBox,
  PageContentBox,
  BlockBox,
  Img,
} from "./home.style";
import Sidebar from "../common/sidebar/sidebar";
import Routingbar from "../common/routingbar/routingbar";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { useMutation } from "@tanstack/react-query";
import spaceRepo from "../../repositories/space.repository";

import { setCurrentPage } from "../../features/space.slice";
import useAuthorize from "../../_components/common/hooks/useAuthorize";

export default function Home() {
  const { getAccessToken } = useAuthorize();

  const currentPage = useSelector((state) => state.space.currentPage);
  const sidebarState = useSelector((state) => state.state.sidebarState);

  const [text, setText] = useState("");
  const [title, setTitle] = useState("");
  const [intervalId, setIntervalId] = useState(null);

  const mutation = useMutation({ mutationFn: spaceRepo.updatePage });

  const dispatch = useDispatch();

  // useEffect(() => {
  //   if (intervalId) {
  //     clearInterval(intervalId);
  //   }
  //   const pageUuid = currentPage.uuid;
  //   const id = setInterval(() => {
  //     mutation.mutate({ pageUuid, title, text }).then((result) => {
  //       dispatch(setCurrentPage(result.data));
  //     });
  //   }, 3000);

  //   setIntervalId(id);

  //   return () => {
  //     clearInterval(id);
  //   };
  // }, [text, title, mutation]);

  const onTitleChangeHandler = (title) => {
    setTitle(title);
    const pageUuid = currentPage.uuid;
    const accessToken = getAccessToken();
    mutation
      .mutateAsync({ pageUuid, title, text, accessToken })
      .then((result) => {
        console.log(result.data);
        dispatch(setCurrentPage(result.data));
      });
  };

  useEffect(() => {
    setTitle(currentPage.title);
    setText(currentPage.text);
  }, [currentPage]);

  return (
    <SpaceBox>
      {sidebarState ? <Sidebar /> : null}
      <PageBox state={sidebarState}>
        <Routingbar />
        <PageContentBox>
          <Img state={sidebarState} src={currentPage.background} />
          <IconBox state={sidebarState}>
            <img style={{ width: "90px" }} src={currentPage.icon} alt="" />
          </IconBox>
          <TitleTextarea
            value={title}
            onChange={(e) => {
              onTitleChangeHandler(e.target.value);
            }}
            state={sidebarState}
            placeholder="Untitled"
            maxLength={27}
          />
          <BlockBox>
            <ContentAutoArea
              value={text}
              onChange={(e) => {
                setText(e.target.value);
              }}
              placeholder="Write something, or press 'space' for AI, '/' for commands..."
            />
          </BlockBox>
        </PageContentBox>
      </PageBox>
    </SpaceBox>
  );
}
