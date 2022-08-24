import React from 'react'
import './Nav.css';
import { Link } from "react-router-dom";

const Nav = () => {
  return (
    <nav className='nav'>
      <ul>
        <li>
          <Link to="/clientes"><button>Clientes</button></Link>
        </li>
        <li>
          <Link to="/cuentas"><button>Cuentas</button></Link>
        </li>
        <li>
          <Link to="/clientes"><button>Movimientos</button></Link>
        </li>
        <li>
          <Link to="/clientes"><button>Reportes</button></Link>
        </li>
      </ul>
    </nav>
  )
}

export default Nav;