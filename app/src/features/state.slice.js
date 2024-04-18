import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  sidebarState: true,
  updateState: false,
  updateBlockState: false,
};

const stateSlice = createSlice({
  name: "state",
  initialState,
  reducers: {
    setSidebarState: (state, action) => {
      state.sidebarState = action.payload;
    },
    setUpdateState: (state, action) => {
      state.updateState = action.payload;
    },
    setUpdateBlockState: (state, action) => {
      state.updateBlockState = action.payload;
    },
  },
});

export const { setSidebarState, setUpdateState, setUpdateBlockState } =
  stateSlice.actions;
export default stateSlice.reducer;
