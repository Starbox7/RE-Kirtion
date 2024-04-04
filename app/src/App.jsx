import { Provider } from "react-redux";
import store from "./features/store.js";
import Router from "./(route)/Router.js";

function App() {
  return (
    <Provider store={store}>
      <Router />
    </Provider>
  );
}

export default App;
