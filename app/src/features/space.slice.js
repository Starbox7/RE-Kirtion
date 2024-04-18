import { createSlice } from "@reduxjs/toolkit";

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
  currentPageBlockList: [],
  currentWorkspace: {
    uuid: "",
    name: "",
    logo: "",
    plan: "",
    domain: "",
    created: "",
    userUuid: "",
  },
  blockListInPersonalPageList: [],
  pageListInTeamspaceList: [],
};

const spaceSlice = createSlice({
  name: "space",
  initialState,
  reducers: {
    setCurrentPage: (state, action) => {
      let {
        uuid,
        title,
        icon,
        background,
        text,
        created,
        route,
        soft_delete,
        personalspace_uuid,
        teamspace_uuid,
        parent_page_uuid,
      } = action.payload;

      // if (title == null) title = "";
      // if (icon == null) icon = "";
      // if (background == null) background = "";
      // if (text == null) text = "";
      // if (personalspace_uuid == null) personalspace_uuid = "";
      // if (teamspace_uuid == null) teamspace_uuid = "";
      // if (parent_page_uuid == null) parent_page_uuid = "";

      state.currentPage = {
        uuid,
        title,
        icon,
        background,
        text,
        created,
        route,
        softDelete: soft_delete,
        personalspaceUuid: personalspace_uuid,
        teamspaceUuid: teamspace_uuid,
        parentPageUuid: parent_page_uuid,
      };
    },
    setCurrentPageBlockList: (state, action) => {
      state.currentPageBlockList = action.payload;
    },
    setCurrentWorkspace: (state, action) => {
      const { uuid, name, logo, plan, domain, created, user_uuid } =
        action.payload;
      state.currentWorkspace = {
        uuid,
        name,
        logo,
        plan,
        domain,
        created,
        userUuid: user_uuid,
      };
    },
    // setPersonalspacePageList: (state, action) => {
    //   const data = acion.payload;
    //   // if (data == null) data = [];
    //   state.personalspacePageList = data;
    // },
    setBlockListInPersonalPageList: (state, action) => {
      state.blockListInPersonalPageList = action.payload;
    },
    setPageListInTeamspaceList: (state, action) => {
      const data = action.payload;
      // if (data == null) data = [];
      state.pageListInTeamspaceList = data;
    },
  },
});

export const {
  setCurrentPage,
  setCurrentWorkspace,
  // setPersonalspacePageList,
  setPageListInTeamspaceList,
  setCurrentPageBlockList,
  setBlockListInPersonalPageList,
} = spaceSlice.actions;
export default spaceSlice.reducer;
