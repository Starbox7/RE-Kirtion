import Home from "../_components/home/home";
import { useQuery } from "@tanstack/react-query";
import spaceRepo from "../repositories/space.repository";
import useAuthorize from "../_components/common/hooks/useAuthorize";
import { useDispatch, useSelector } from "react-redux";
import {
  setCurrentPage,
  setCurrentWorkspace,
  // setPersonalspacePageList,
  setPageListInTeamspaceList,
  setCurrentPageBlockList,
  setBlockListInPersonalPageList,
} from "../features/space.slice";
import { useEffect } from "react";
import { Box, CircularProgress } from "@mui/material";
import { setUpdateBlockState, setUpdateState } from "../features/state.slice";

export default function HomePage() {
  const dispatch = useDispatch();
  const { getAccessToken } = useAuthorize();
  const { isLoading, data } = useQuery({
    queryKey: ["spaceInit"],
    queryFn: () => spaceRepo.spaceInit(getAccessToken()),
  });

  useEffect(() => {
    if (!isLoading) {
      dispatch(setUpdateState(true));
      dispatch(setUpdateBlockState(true));
      dispatch(setCurrentPage(data.data.current_page));
      dispatch(setCurrentPageBlockList(data.data.current_page_block_list));
      dispatch(setCurrentWorkspace(data.data.current_workspace));
      // dispatch(setPersonalspacePageList(data.data.personal_page_list));
      dispatch(
        setBlockListInPersonalPageList(
          data.data.block_list_in_personal_page_list
        )
      );
      dispatch(
        setPageListInTeamspaceList(data.data.page_list_in_teamspace_list)
      );
    }
  }, [data]);

  // useEffect(() => {}, [currentPage]);

  return isLoading ? (
    <Box
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "100vw",
        height: "100vh",
      }}
    >
      <CircularProgress />
    </Box>
  ) : (
    <Home />
  );
}
