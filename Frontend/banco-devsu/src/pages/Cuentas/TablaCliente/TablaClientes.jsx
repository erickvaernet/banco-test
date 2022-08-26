import React, { useState, useEffect } from "react";
import Table from "../../../components/Table/Table";
import FlechasPaginacion from "../../../components/FlechasPaginacion/FlechasPaginacion";
import { listService } from "../../../service/listService";
import "../../Clientes/Clientes.css";
import "./TablaClientes.css";
import camposClientes from "../../Clientes/CamposClientes.js";
import Modal from "../../../components/Modal/Modal";

const TablaClientes = ({tablaClientes,setTablaClientes,setClienteId}) => {
  const uri="/clientes";
  const nombresProps = camposClientes.map((campos) => campos["nombre"]);
  const columnas = camposClientes.map((campos) => campos["nombreForm"]);
  const [filas, setFilas] = useState(null);
  const [numeroPagina, setNumeroPagina] = useState(1);
  const [maxPaginas, setMaxPaginas] = useState(null);

  useEffect(() => {
    listService(uri, numeroPagina)?.then((data) => {
      const { resultados, cantidad, tamanioDePagina } = data;
      setFilas(resultados);
      setMaxPaginas(Math.ceil(cantidad / tamanioDePagina));
    });
  }, [numeroPagina])

  const onSelectRow=(event)=>{
    setClienteId(event.target.value)
    event.target.classList.add(".cliente-seleccionado")
  }
  return (
    <Modal open={tablaClientes} setOpen={setTablaClientes}>
      <div className="contenedor-tabla-flechas">
          {filas && columnas ? (
          <Table
              columnas={columnas}
              nombresProps={nombresProps}
              filas={filas}
              CRUDTable={false}
              onSelectRow={onSelectRow}
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

export default TablaClientes