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
import { deleteByIdService } from "../../service/deleteByIdService";
import Modal from "../../components/Modal/Modal";

const Clientes = () => {
  const uri = "/clientes";
  const nombresProps = camposClientes.map((campos) => campos["nombre"]);
  const columnas = camposClientes.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [reRender, setReRender] = useState(1);
  const [errores, setErrores] = useState(null);
  const [erroresOpen, setErroresOpen] = useState(false);

  useEffect(() => {
    listService(uri, numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina, reRender]);

  const onSubmit = (data) => {
    postService(uri, data)
      .then(() => {
        setOpenModal(false);
        setReRender(reRender + 1);
      })
      .catch((error) => {
        setErrores(error);
        setErroresOpen(true);
      });
  };

  const handleClickNuevoRegistro = () => {
    setOpenModal(true);
  };
  const onClickDelete = (event) => {
    deleteByIdService(uri, event.target.value).then(() =>
      setReRender(reRender + 1)
    );
  };
  const onClickEdit = (event) => {
    //next-sprint
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
          <Modal open={erroresOpen} setOpen={setErroresOpen}>
            <p className={"modalErrors"}>
              {errores ? errores.toString() : null}
            </p>
          </Modal>
          <div className="contenedor-tabla-flechas">
            {filas && columnas ? (
              <Table
                columnas={columnas}
                nombresProps={nombresProps}
                filas={filas}
                CRUDTable={true}
                onClickDelete={onClickDelete}
                onClickEdit={onClickEdit}
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
