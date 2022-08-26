import React, { useEffect, useState } from "react";
import Button from "../../components/Button/Button";
import FlechasPaginacion from "../../components/FlechasPaginacion/FlechasPaginacion";
import FlexBox from "../../components/FlexBox/FelxBox";
import H1 from "../../components/H1/H1";
import ModalForm from "../../components/ModalForm/ModalForm";
import Nav from "../../components/Nav/Nav";
import Table from "../../components/Table/Table";
import { listService } from "../../service/listService";
import { postService } from "../../service/postService";
import CamposReportes from "./CamposReportes";
import "./Reportes.css";


const Reportes = () => {
  const nombresProps = CamposReportes.map((campos) =>campos["nombre"]);
  const columnas = CamposReportes.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);
  const [openModal, setOpenModal] = useState(false);

  useEffect(() => {
    listService("/reportes", numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      //resultados.forEach((e)=>e["cliente"]=e["cliente"]["nombres"])
      console.log(tamanioDePagina)
      setFilas(resultados);      
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina,openModal]);

  const onSubmit = (data) => {
    console.log(data);
  };

  const handleClickNuevoRegistro = () => {
    setOpenModal(true);
  };

  return (
    <div>
      <FlexBox>
        <Nav />
        <div className="main">
          <H1>Reportes</H1>
          <div className="contenedor-botonNuevo">
            <Button type="input" onClick={handleClickNuevoRegistro}>
              Buscar por fecha
            </Button>
          </div>        
          <div className="contenedor-tabla-flechas">
            {filas && columnas ? (
              <Table
                columnas={columnas}
                nombresProps={nombresProps}
                filas={filas}
              />
            ) : null}
            <FlechasPaginacion
              numeroPagina={numeroPagina}
              maximoPaginas={maxPaginas}
              setNumeroPagina={setNumeroPagina}
            />
          </div>
        </div>
      </FlexBox>
    </div>
  );
};

export default Reportes;