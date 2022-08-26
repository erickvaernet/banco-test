import React from "react";
import "./FlechasPaginacion.css";

const FlechasPaginacion = (props) => {
    const {numeroPagina,maximoPaginas,setNumeroPagina,...rest}=props;
    const handleClickPrev = () => {
      console.log(numeroPagina);
      if(numeroPagina>1) setNumeroPagina(numeroPagina-1);
    }
    const handleClickNext = () => {
      if(numeroPagina<maximoPaginas) setNumeroPagina(numeroPagina+1);
    }   

  return (
    <div className="flechas">
      <button onClick={handleClickPrev}>{"<"}</button>
      <p>
        {maximoPaginas?.toString()=="NaN"?(0+"/"+0):(numeroPagina+"/"+maximoPaginas)}
      </p>
      <button onClick={handleClickNext} >{">"}</button>
    </div>
  );
};


export default FlechasPaginacion;