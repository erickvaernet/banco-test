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
            return(
              <th key={index}>{element}</th>
            ); 
          })}
        </tr>
      </thead>
      <tbody>
        {rows.map((element,id)=>{
            return(
              <tr key={id}>
                {columns.map((nombreProp,index)=>{
                  <td key={index}>
                    {element[nombreProp]}
                  </td>
                })}
              </tr>
              ); 
          })}
      </tbody>
    </table>
  )
}

export default Table;