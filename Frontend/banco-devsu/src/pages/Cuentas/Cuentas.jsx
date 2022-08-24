import React from "react";

import "./Cuentas.css";

const Cuentas = () => {
  const [columnas,setColumnas] = useState([]);
  /*const columnas=[
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
  ]*/
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

export default Cuentas;
