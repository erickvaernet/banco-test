import React, { useEffect, useState } from 'react'
import './Table.css';
import {listService} from '../../service/listService'

const Table = ({columns,rows,...props}) => {
  const [] = useState(null);

  return (
    <table className="table-primary">
      <thead>
        <tr>
          {columns.map((element,index)=>{
            if(element==="contrasenia") return <th key={index}>{"contraseña"}</th>
            return(
              <th key={index}>{element}</th>
            ); 
          })}
        </tr>
      </thead>
      <tbody>
        {rows.map((element,id)=>{
          if(columns)
            return(
              <tr key={id}>
                {columns.map((nombreProp,index)=>{
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