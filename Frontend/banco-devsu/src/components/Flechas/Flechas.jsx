import React from "react";
import "./Flechas.css";

const Flechas = (props) => {
    const {numeroPagina,setNumeroPagina,...rest}=props
  return (
    <div className="flechas">
      <button>{"<"}</button>
      <p>{numeroPagina}</p>
      <button>{">"}</button>
    </div>
  );
};


export default Flechas;