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
import CamposMovimientos from "./CamposMovimientos";
import "./Movimientos.css";
import TablaCuentasModal from "./TablaCuentasModal/TablaCuentasModal";

const Movimientos = () => {
  const uri = "/movimientos";
  const nombresProps = CamposMovimientos.map((campos) => {
    return campos["subprop"] ? campos["nombre"] : campos["nombre"];
  });
  const columnas = CamposMovimientos.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [reRender, setReRender] = useState(1);
  const [cuentaSelect, setCuentaSelect] = useState(null);
  const [tablaCuentas, setTablaCuentas] = useState(false);
  const [errores, setErrores] = useState(null);
  const [erroresOpen, setErroresOpen] = useState(false);

  useEffect(() => {
    listService(uri, numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      resultados.forEach((e) => (e["cuenta"] = e["cuenta"]["numeroCuenta"]));
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina, reRender]);

  const onSubmit = (data) => {
    data["cuenta"] = {
      numeroCuenta: cuentaSelect["id"],
    };
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
  const handleNuevoClick = () => {
    setCuentaSelect(null);
    setTablaCuentas(true);
  };

  return (
    <div>
      <FlexBox>
        <Nav />
        <div className="main">
          <H1>Movimientos</H1>
          <div className="contenedor-botonNuevo">
            <Button type="input" onClick={handleClickNuevoRegistro}>
              Nuevo
            </Button>
          </div>
          <ModalForm
            onSubmit={onSubmit}
            campos={CamposMovimientos}
            titulo="Nuevo Movimiento"
            name={columnas}
            openModal={openModal}
            setOpenModal={setOpenModal}
          >
            <div className="contenedor-seleccionarComponente">
              <label>Seleccionar cuenta:</label>
              {cuentaSelect ? <p>{cuentaSelect["nombres"]}</p> : null}
              <Button className="button-secondary" onClick={handleNuevoClick}>
                Seleccionar Cuenta
              </Button>
            </div>
            <TablaCuentasModal
              setTablaCuentas={setTablaCuentas}
              tablaCuentas={tablaCuentas}
              setCuentaSelect={setCuentaSelect}
            />
          </ModalForm>
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

export default Movimientos;
