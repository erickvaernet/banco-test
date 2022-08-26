import React, { useEffect, useState } from "react";
import Button from "../../components/Button/Button";
import FlechasPaginacion from "../../components/FlechasPaginacion/FlechasPaginacion";
import FlexBox from "../../components/FlexBox/FelxBox";
import H1 from "../../components/H1/H1";
import Modal from "../../components/Modal/Modal";
import ModalForm from "../../components/ModalForm/ModalForm";
import Nav from "../../components/Nav/Nav";
import Table from "../../components/Table/Table";
import { deleteByIdService } from "../../service/deleteByIdService";
import { listService } from "../../service/listService";
import { postService } from "../../service/postService";
import CamposCuentas from "./CamposCuentas";

import "./Cuentas.css";
import TablaClientesModal from "./TablaClientesModal/TablaClientesModal";

const Cuentas = () => {
  const uri = "/cuentas";
  const nombresProps = CamposCuentas.map((campos) => campos["nombre"]);
  const columnas = CamposCuentas.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [reRender, setReRender] = useState(1);
  const [clienteSelect, setClienteSelect] = useState(null);
  const [tablaClientes, setTablaClientes] = useState(false);
  const [errores, setErrores]=  useState(null);
  const [erroresOpen, setErroresOpen]=  useState(false);

  useEffect(() => {
    listService(uri, numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      resultados.forEach((e) => (e["cliente"] = e["cliente"]["nombres"]));
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina, reRender]);

  const onSubmit = (data) => {
    data["cliente"] = {
      "id": clienteSelect["id"],
    };
    postService(uri, data).then(() => {
      setOpenModal(false);
      setReRender(reRender + 1);
    }).catch((error)=>{
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
  const handleNuevoClick = () => {
    setClienteSelect(null);
    setTablaClientes(true);
  };

  return (
    <div>
      <FlexBox>
        <Nav />
        <div className="main">
          <H1>Cuentas</H1>
          <div className="contenedor-botonNuevo">
            <Button type="input" onClick={handleClickNuevoRegistro}>
              Nuevo
            </Button>
          </div>
          <ModalForm
            onSubmit={onSubmit}
            campos={CamposCuentas}
            titulo="Nueva Cuenta"
            name={columnas}
            openModal={openModal}
            setOpenModal={setOpenModal}
          >
            <div className="contenedor-seleccionarComponente">
              <label>Seleccionar cliente:</label>
              {clienteSelect ? <p>{clienteSelect["nombres"]}</p> : null}
              <Button className="button-secondary" onClick={handleNuevoClick}>
                Seleccionar
              </Button>
            </div>
            <TablaClientesModal
              setTablaClientes={setTablaClientes}
              tablaClientes={tablaClientes}
              setClienteSelect={setClienteSelect}
            />
          </ModalForm>
          <Modal open={erroresOpen}  setOpen={setErroresOpen}><p className={"modalErrors"}>{errores? errores.toString():null}</p></Modal>
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

export default Cuentas;
