import { Global } from "@emotion/react";
import { global } from "./styles/global";
import { Route, Routes } from "react-router-dom";
import AdminRoute from "./routes/AdminRoute/AdminRoute";
import UserRoute from "./routes/UserRoute/UserRoute";
import AdminMainRoute from "./routes/AdminRoute/AdminMainRoute";

function App() {
  return (
    <>
      <Global styles={global} />
      <Routes>
        <Route path="/*" element={<UserRoute />} />
        <Route path="/admin/main/*" element={<AdminMainRoute />} />
        <Route path="/admin/*" element={<AdminRoute />} />
      </Routes>
    </>
  );
}

export default App;