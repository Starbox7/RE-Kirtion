import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./auth.slice";
import spaceSlice from "./space.slice";

const store = configureStore({
  reducer: {
    auth: authSlice,
    space: spaceSlice,
    // counter: counterSlice.reducer,
  },
});

export default store;

//Typescript
// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;
