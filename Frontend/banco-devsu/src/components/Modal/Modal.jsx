import React, { Children, useState } from "react";
import "./Modal.css";
import Button from "../Button/Button"
import H1 from "../H1/H1";
import InputFormText from "../InputFormText/InputFormText";

const Modal = (props) => {
  const {children,open,setOpen, ...rest } = props;
  const className= open? "modal open":"modal"
  
  const handleCloseModal= ()=>{
    setOpen(false);
  }
  return (
    <div className={className} >
      <div className="modal-bg modal-exit"></div>
      <div className="modal-container">
        {children}
        <button className="modal-close modal-exit" onClick={handleCloseModal}>X</button>
      </div>
    </div>
  );
};

export default Modal;
