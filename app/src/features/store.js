import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./auth.slice";

const store = configureStore({
  reducer: {
    auth: authSlice,
    // counter: counterSlice.reducer,
  },
});

export default store;

//Typescript
// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;
