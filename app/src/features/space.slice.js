import { createSlice } from "@reduxjs/toolkit";
import authSlice from "./auth.slice";

const initialState = {
  currentPage: {
    uuid: "",
    title: "",
    icon: "",
    background: "",
    text: "",
    created: "",
    route: "",
    softDelete: false,
    personalspaceUuid: "",
    teamspaceUuid: "",
    parentPageUuid: "",
  },
  currentWorkspace: {
    uuid: "",
    name: "",
    logo: "",
    plan: "",
    domain: "",
    created: "",
    userUuid: "",
  },
  personalspacePageList: [],
  pageListInTeamspaceList: [],
};

const spaceSlice = createSlice({
  name: "space",
  initialState,
  reducers: {
    setCurrentPage: (state, action) => {
      const {
        uuid,
        title,
        icon,
        background,
        text,
        created,
        route,
        softDelete,
        personalspaceUuid,
        teamspaceUuid,
        parentPageUuid,
      } = action.payload;
      state.currentPage = {
        uuid,
        title,
        icon,
        background,
        text,
        created,
        route,
        softDelete,
        personalspaceUuid,
        teamspaceUuid,
        parentPageUuid,
      };
    },
    setCurrentWorkspace: (state, action) => {
      const { uuid, name, logo, plan, domain, created, userUuid } =
        action.payload;
      state.currentWorkspace = {
        uuid,
        name,
        logo,
        plan,
        domain,
        created,
        userUuid,
      };
    },
    setPersonalspacePageList: (state, acion) => {
      const data = acion.payload;
      state.personalspacePageList = data;
    },
    setPageListInTeamspaceList: (state, action) => {
      const data = action.payload;
      state.pageListInTeamspaceList = data;
    },
  },
});

export const {
  setCurrentPage,
  setCurrentWorkspace,
  setPersonalspacePageList,
  setPageListInTeamspaceList,
} = spaceSlice.actions;
export default authSlice.reducer;
