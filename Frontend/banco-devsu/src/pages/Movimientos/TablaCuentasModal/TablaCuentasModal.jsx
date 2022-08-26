import React, { useState, useEffect } from "react";
import Table from "../../../components/Table/Table";
import FlechasPaginacion from "../../../components/FlechasPaginacion/FlechasPaginacion";
import { listService } from "../../../service/listService";
import "../../Cuentas/Cuentas.css";
import "./TablaCuentasModal.css";
import camposCuentas from "../../Cuentas/CamposCuentas.js";
import Modal from "../../../components/Modal/Modal";
import { getByIdService } from "../../../service/getByIdService";

const TablaCuentasModal = ({setCuentaSelect,tablaCuentas,setTablaCuentas,setCuentaId, setCuentaNombre}) => {
  const uri="/cuentas";
  const nombresProps = camposCuentas.map((campos) => campos["nombre"]);
  const columnas = camposCuentas.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);

  useEffect(() => {
    listService(uri, numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      resultados.forEach((e)=>e["cliente"]=e["cliente"]["nombres"])
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina])

  const handleOnClick=(id,elemento)=>{
    setCuentaSelect({"id":id,"nombres":elemento["cliente"]+": "+elemento["tipo"]})
    setTablaCuentas(false);
  }

  return (
    <Modal open={tablaCuentas} setOpen={setTablaCuentas}>
      <div className="contenedor-tabla-flechas">
          {filas && columnas ? (
          <Table
              columnas={columnas}
              nombresProps={nombresProps}
              filas={filas}
              CRUDTable={false}
              handleOnClick={handleOnClick}
              setCuentaNombre={setCuentaNombre}
          />
          ) : null}
          <FlechasPaginacion
          numeroPagina={numeroPagina}
          maximoPaginas={maxPaginas}
          setNumeroPagina={setNumeroPagina}
          />
      </div>
    </Modal>
  )
}

export default TablaCuentasModal;