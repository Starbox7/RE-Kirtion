import { Provider } from "react-redux";
import store from "./features/store.js";
import Router from "./(route)/Router.js";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
// import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

function App() {
  const queryClient = new QueryClient();
  return (
    <QueryClientProvider client={queryClient}>
      <Provider store={store}>
        {/* <ReactQueryDevtools initialIsOpen={true} /> */}
        <Router />
      </Provider>
    </QueryClientProvider>
  );
}

export default App;
