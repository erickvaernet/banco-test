import React from "react";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Clientes from "../pages/Clientes/Clientes";
import Cuentas from "../pages/Cuentas/Cuentas";
import Movimientos from "../pages/Movimientos/Movimientos";
import Reportes from "../pages/Reportes/Reportes";

//import Home from "../pages/Home/Home";

const AdminRoutes = () => {

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Clientes />} />
          <Route path="/*" element={<Clientes />} />
          <Route path="/cuentas" element={<Cuentas />} />
          <Route path="/movimientos" element={<Movimientos />} />
          <Route path="/reportes" element={<Reportes />} /> 
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default AdminRoutes;
