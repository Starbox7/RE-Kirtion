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

import {
  setCurrentPage,
  // setPersonalspacePageList,
  setBlockListInPersonalPageList,
  setCurrentWorkspace,
  setPageListInTeamspaceList,
} from "../../features/space.slice";
import useAuthorize from "../../_components/common/hooks/useAuthorize";
import { setUpdateState } from "../../features/state.slice";
import Block from "../common/block";
import ListView from "../common/listview";

//de bounce logic 생각하기
export default function Home() {
  const { getAccessToken } = useAuthorize();

  const currentPage = useSelector((state) => state.space.currentPage);
  const currentPageBlocks = useSelector(
    (state) => state.space.currentPageBlockList
  );
  const sidebarState = useSelector((state) => state.state.sidebarState);
  const updateState = useSelector((state) => state.state.updateState);

  const [text, setText] = useState("");
  const [title, setTitle] = useState("");

  const mutation = useMutation({ mutationFn: spaceRepo.updatePage });

  const dispatch = useDispatch();

  const onTitleChangeHandler = (title) => {
    setTitle(title);
    const pageUuid = currentPage.uuid;
    const accessToken = getAccessToken();
    mutation
      .mutateAsync({ pageUuid, title, text, accessToken })
      .then((result) => {
        dispatch(setCurrentPage(result.data.current_page));
        dispatch(setCurrentWorkspace(result.data.current_workspace));
        dispatch(
          setBlockListInPersonalPageList(
            result.data.block_list_in_personal_page_list
          )
        );
        dispatch(
          setPageListInTeamspaceList(result.data.page_list_in_teamspace_list)
        );
      });
  };

  useEffect(() => {
    if (updateState) {
      setTitle(currentPage.title);
      setText(currentPage.text);
      dispatch(setUpdateState(false));
    }
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
            {currentPageBlocks.map((block) => (
              <Block
                key={block.uuid}
                blockData={block}
                currentPage={currentPage}
                title={title}
              />
            ))}
          </BlockBox>
        </PageContentBox>
      </PageBox>
    </SpaceBox>
  );
}
