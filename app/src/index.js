import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { Global, css } from "@emotion/react";

const globalStyles = css`
  body,
  html,
  #root {
    margin: 0;
  }

  body::-webkit-scrollbar {
    display: none;
  }
`;

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <>
    <Global styles={globalStyles} />
    <App />
  </>
);
reportWebVitals();
