import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  sidebarState: true,
};

const stateSlice = createSlice({
  name: "state",
  initialState,
  reducers: {
    setSidebarState: (state, action) => {
      state.sidebarState = action.payload;
    },
  },
});

export const { setSidebarState } = stateSlice.actions;
export default stateSlice.reducer;
