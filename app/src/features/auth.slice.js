import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  authenticationState: false,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setAuthenticationState: (state, action) => {
      const authenticationState = action.payload;
      state.authenticationState = authenticationState;
    },
    // setAuthData: (state, action) => {
    //   const { email, password } = action.payload;
    //   state.email = email;
    //   state.password = password;
    // },
    // setEmail: (state, action) => {
    //   const { email } = action.payload;
    //   state.email = email;
    // },
    // setPassword: (state, action) => {
    //   const { password } = action.payload;
    //   state.password = password;
    // },
  },
});

export const {
  // setAuthData, setEmail, setPassword
  setAuthenticationState,
} = authSlice.actions;
export default authSlice.reducer;
