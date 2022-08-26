import React, { useEffect, useState } from 'react'
import CrudButtons from "../CrudButtons/CrudButtons";
import './Table.css';

const Table = ({onSelectRow,CRUDTable,onClickDelete,onClickEdit,columnas,filas,nombresProps,...props}) => {

  return (
    <table className="table-primary">
      <thead>
        <tr>
          { columnas.map((element,index)=>{
            return(
              <th key={index}>{element}</th>
            ); 
          })}
          {CRUDTable? <th>Opciones</th>:null}
        </tr>
      </thead>
      <tbody>
        {filas.map((element,index)=>{
          if( columnas)
            return(
              <tr key={index} id={element["id"]?element["id"]:element["numeroCuenta"]} onClick={onSelectRow}>
                {nombresProps.map((nombreProp,index)=>{
                  if(nombreProp=="contrasenia") return <td key={index}>********</td>
                  return (<td key={index}>
                    {element[nombreProp].toString()}
                  </td>)
                })}
                {CRUDTable? <td><CrudButtons id={element["id"]?element["id"]:element["numeroCuenta"]} onClickDelete={onClickDelete} onClickEdit={onClickEdit}/></td>:null}
              </tr>
              ); 
          })}
      </tbody>
    </table>
  )
}

export default Table;