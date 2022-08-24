import React, { useState,useEffect } from "react";
import H1 from "../../components/H1/H1";
import Table from "../../components/Table/Table";
import Flechas from "../../components/Flechas/Flechas";
import { listService } from "../../service/listService";
import "./Clientes.css";
import Nav from "../../components/Nav/Nav";
import FlexBox from "../../components/FlexBox/FelxBox";

const Clientes = () => {

  const [columnas,setColumnas] = useState([]);
  const [filas,setFilas] = useState(null);
  const [numeroPagina,setNumeroPagina] = useState(0);
  
  
  useEffect(()=>{
    listService("clientes",numeroPagina).then((data)=>{
      setColumnas(Object.keys(data[0]));
      setFilas(data);
    })
  },[numeroPagina]);
  

  return (
    <div>
    <FlexBox>
      <Nav/>
      <div className="main">
        <H1>Clientes</H1>
        <div className="contenedor-tabla-flechas">
          {filas && columnas ? <Table columns={columnas} rows={filas}/>:null}
          
          <Flechas numeroPagina={numeroPagina} setNumeroPagina={setNumeroPagina}/>
        </div>
      </div>
    </FlexBox>
  </div>
  );
};

export default Clientes;
