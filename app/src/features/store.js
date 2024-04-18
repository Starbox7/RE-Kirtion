import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./auth.slice";
import spaceSlice from "./space.slice";
import stateSlice from "./state.slice";

const store = configureStore({
  reducer: {
    auth: authSlice,
    space: spaceSlice,
    state: stateSlice,
    // counter: counterSlice.reducer,
  },
});

export default store;

//Typescript
// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;
