import React, { useEffect, useState } from 'react'
import './Table.css';
import {listService} from '../../service/listService'

const Table = ({columns,rows,...props}) => {
  const [] = useState(null);

  return (
    <Table className="table-primary">
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
        {rows.map((element)=>{
            return(
              <tr key={element.id}>
                {columns.map((nombreProp)=>{
                  <td>
                    {element[nombreProp]}
                  </td>
                })}
              </tr>
              ); 
          })}
      </tbody>
    </Table>
  )
}

export default Table;