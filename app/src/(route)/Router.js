/** import library */
import { BrowserRouter, Route, Routes } from "react-router-dom";
/** import page */
import Login from "../_pages/login.page";
import Register from "../_pages/register.page";

/** Route Area */
export default function Router() {
  return (
    <BrowserRouter>
      <Routes>
        {/* <Route path="/console" element={signState ? <Console /> : <Login />} />*/}
        <Route path="/signup" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/*" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}
