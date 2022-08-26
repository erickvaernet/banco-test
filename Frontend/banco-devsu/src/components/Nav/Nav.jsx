import React from "react";
import "./Nav.css";
import { Link } from "react-router-dom";

const Nav = () => {
  return (
    <nav className="nav">
      <ul>
        <li>
          <Link to="/clientes">
            <p className="link-button">Clientes</p>
          </Link>
        </li>
        <li>
          <Link to="/cuentas">
            <p className="link-button">Cuentas</p>
          </Link>
        </li>
        <li>
          <Link to="/movimientos">
            <p className="link-button">Movimientos</p>
          </Link>
        </li>
        <li>
          <Link to="/reportes">
            <p className="link-button">Reportes</p>
          </Link>
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
