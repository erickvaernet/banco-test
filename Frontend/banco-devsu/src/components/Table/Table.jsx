import React, { useEffect, useState } from 'react'
import './Table.css';

const Table = ({ columnas,filas,nombresProps,...props}) => {
  return (
    <table className="table-primary">
      <thead>
        <tr>
          { columnas.map((element,index)=>{
            return(
              <th key={index}>{element}</th>
            ); 
          })}
        </tr>
      </thead>
      <tbody>
        {filas.map((element,id)=>{
          if( columnas)
            return(
              <tr key={id}>
                {nombresProps.map((nombreProp,index)=>{
                  if(nombreProp=="contrasenia") return <td key={index}>********</td>
                  return (<td key={index}>
                    {element[nombreProp].toString()}
                  </td>)
                })}
              </tr>
              ); 
          })}
      </tbody>
    </table>
  )
}

export default Table;