import React, { useState, useEffect } from "react";
import H1 from "../../components/H1/H1";
import Table from "../../components/Table/Table";
import FlechasPaginacion from "../../components/FlechasPaginacion/FlechasPaginacion";
import { listService } from "../../service/listService";
import "./Clientes.css";
import Nav from "../../components/Nav/Nav";
import FlexBox from "../../components/FlexBox/FelxBox";
import Button from "../../components/Button/Button";
import ModalForm from "../../components/ModalForm/ModalForm";
import camposClientes from "./CamposClientes.js";
import { postService } from "../../service/postService";

const Clientes = () => {
  const nombresProps = camposClientes.map((campos) => campos["nombre"]);
  const columnas = camposClientes.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);
  const [openModal, setOpenModal] = useState(false);

  useEffect(() => {
    listService("/clientes", numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina]);

  const onSubmit = (data) => {
    postService("/clientes", data).then(setOpenModal(false));
  };

  const handleClickNuevoRegistro = () => {
    setOpenModal(true);
  };

  return (
    <div>
      <FlexBox>
        <Nav />
        <div className="main">
          <H1>Clientes</H1>
          <div className="contenedor-botonNuevo">
            <Button type="input" onClick={handleClickNuevoRegistro}>
              Nuevo
            </Button>
          </div>
          <ModalForm
            onSubmit={onSubmit}
            campos={camposClientes}
            titulo="Nuevo Cliente"
            name={columnas}
            openModal={openModal}
            setOpenModal={setOpenModal}
          />
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

export default Clientes;
