import React from "react";
import "./H1.css";

const H1 = (props) => {
  const { children, ...rest } = props;
  return <h1 className="title">{children}</h1>;
};

export default H1;
