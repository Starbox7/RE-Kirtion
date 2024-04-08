import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  email: "",
  password: "",
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setAuthData: (state, action) => {
      const { email, password } = action.payload;
      state.email = email;
      state.password = password;
    },
    setEmail: (state, action) => {
      const { email } = action.payload;
      state.email = email;
    },
    setPassword: (state, action) => {
      const { password } = action.payload;
      state.password = password;
    },
  },
});

export const { setAuthData, setEmail, setPassword } = authSlice.actions;
export default authSlice.reducer;
