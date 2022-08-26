import React from "react";
import "./FlexBox.css";
const Logo = "/assets/Banco.png";

const FlexBox = (props) => {
  const { primary, children, ...rest } = props;
  return <div className="separator-nav-main">{children}</div>;
};

export default FlexBox;
