import React from 'react'
import './FelxBox.css';
const Logo = "/assets/Banco.png";

const FelxBox = (props) => {
  const {primary , children, ...rest} = props;
  return (
    <div className='separator-nav-main'>
      {children}
    </div>
  )
}

export default FelxBox;