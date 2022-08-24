import React, { useState,useEffect } from "react";
import H1 from "../../components/H1/H1";
import Table from "../../components/Table/Table";
import FlechasPaginacion from "../../components/FlechasPaginacion/FlechasPaginacion";
import { listService } from "../../service/listService";
import "./Clientes.css";
import Nav from "../../components/Nav/Nav";
import FlexBox from "../../components/FlexBox/FelxBox";

const Clientes = () => {
  //const [columnas,setColumnas] = useState([]);
  const columnas=[
    "id",	
    "identificacion",	
    "nombres",
    "genero",	
    "fechaNacimiento",	
    "edad",	
    "direccion",	
    "telefono",	
    "contrasenia",	
    "estado"
  ]
  const [filas,setFilas] = useState(null);
  const [numeroPagina,setNumeroPagina] = useState(1);
  const [maxPaginas,setMaxPaginas] = useState(null);
  
  useEffect(()=>{
    listService("clientes",numeroPagina)?.then((data)=>{
      const{resultados,cantidad,tamanioDePagina}=data
      //setColumnas(Object.keys(resultados[0]));
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad/tamanioDePagina));
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
          <FlechasPaginacion numeroPagina={numeroPagina} maximoPaginas={maxPaginas} setNumeroPagina={setNumeroPagina}/>
        </div>
      </div>
    </FlexBox>
  </div>
  );
};

export default Clientes;
