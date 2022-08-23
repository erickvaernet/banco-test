import React from 'react'
import './Nav.css';

const Nav = () => {
  return (
    <nav className='nav'>
      <ul>
        <li>
          <button>Clientes</button>
        </li>
        <li>
          <button>Cuentas</button>
        </li>
        <li>
          <button>Movimientos</button>
        </li>
        <li>
          <button>Reportes</button>
        </li>
      </ul>
    </nav>
  )
}

export default Nav;