import React from "react";
import "./Button.css";

const Button = (props) => {
  const { children, type, className, onClick, ...rest } = props;
  if (type === "input") {
    return (
      <input
        onClick={onClick}
        className={className ? className : "button-primary"}
        type="button"
        value={children}
      />
    );
  } else {
    return (
      <button
        onClick={onClick}
        type={type}
        className={className ? className : "button-primary"}
      >
        {children}
      </button>
    );
  }
};

export default Button;
