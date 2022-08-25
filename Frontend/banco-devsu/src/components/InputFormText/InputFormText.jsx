import React, { useEffect, useState } from "react";
import "./InputFormText.css"

const InputFormText = (props) => {
    const {index,validaciones,nombreLabel,nombre,type,placeholder,register,checkOrRadioValues,...rest}=props
    const [inputs,setInputs] = useState(<input type={type} className="textInput" {...register(nombre, {required: true})}  placeholder={placeholder}/>);
    
    useEffect(()=>{
      if(type=="radio"){
        const inputsMapeados=
        <div className="contenedor-conjuntoRadios">
          {checkOrRadioValues.map((value,index)=>
          <div key={index} className="contenedor-radio">
            <input className="radioInput"  type={type}  value={value} {...register(nombre, {required: true})} />
            <label>{value}</label>
          </div>
          )}
        </div>
        setInputs( inputsMapeados )
      }
    },[])
    
    return (
        <div className="inputText">
            <label > {nombreLabel} </label>
            {inputs}
        </div>
    )
}

export default InputFormText;