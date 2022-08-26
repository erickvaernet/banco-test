import React from "react";
import "./Header.css";
const Logo = "/assets/Banco.png";

const Header = () => {
  return (
    <div className="header">
      <img src={Logo} className="header-logoImg" alt="logo"></img>
      <hr />
    </div>
  );
};

export default Header;
