import React, { useState } from "react";
import H1 from "../../components/H1/H1";
import Table from "../../components/Table/Table";

import "./Clientes.css";

const Clientes = () => {
  const [columnas,setColumnas] = useState(null);
  const [filas,setFilas] = useState(null);
  
  useEffect(()=>{
    listService("clientes",0).then((data)=>{
      setColumnas(Object.keys(data[0]));
      setFilas(data);
    })
  },[]);

  

  return (
    <div className="main">
      <H1>Clientes</H1>
      {filas && columnas ? <Table/>:null}
    </div>
  );
};

export default Clientes;
