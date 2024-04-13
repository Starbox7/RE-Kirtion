/** import library */
import { BrowserRouter, Route, Routes } from "react-router-dom";
/** import page */
import LoginPage from "../_pages/login.page";
import RegisterPage from "../_pages/register.page";
import HomePage from "../_pages/home.page";

/** Route Area */
export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        {/* <Route path="/console" element={signState ? <Console /> : <Login />} />*/}
        <Route path="/signup" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/*" element={<LoginPage />} />
      </Routes>
    </BrowserRouter>
  );
}
