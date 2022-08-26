import React, { useEffect, useState } from 'react'
import CrudButtons from "../CrudButtons/CrudButtons";
import './Table.css';

const Table = ({onSelectRowCuenta,setClienteNombre,onSelectRow,CRUDTable,onClickDelete,onClickEdit,columnas,filas,nombresProps,...props}) => {
  //Sacar fuera del componente
  const handleOnClick= (idElement,element) =>{
    if(onSelectRow) onSelectRow(idElement,element["nombres"]);
    if(onSelectRowCuenta) onSelectRowCuenta(idElement,element["cliente"]["nombres"])
  }
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
          if(columnas){
            const idElement=element["id"]?element["id"]:element["numeroCuenta"];
            return(
              <tr key={index} onClick={()=>handleOnClick(idElement,element)} 
              className={CRUDTable? "":"cliente-seleccionado"} value={idElement} >
                {nombresProps.map((nombreProp,index)=>{
                  if(nombreProp=="contrasenia") return <td key={index}>********</td>
                  return (<td key={index} value={idElement} >
                    {element[nombreProp].toString()}
                  </td>)
                })}
                {CRUDTable? <td><CrudButtons id={element["id"]?element["id"]:element["numeroCuenta"]} onClickDelete={onClickDelete} onClickEdit={onClickEdit}/></td>:null}
              </tr>
              ); 
            }
          })}
      </tbody>
    </table>
  )
}

export default Table;