import { type } from '@testing-library/user-event/dist/type'
import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import Button from '../Button/Button'
import H1 from '../H1/H1'
import InputFormText from '../InputFormText/InputFormText'
import Modal from '../Modal/Modal'
import "./ModalForm.css"

const ModalForm = (props) => {
    const {children,onSubmit,titulo,campos,openModal,setOpenModal,...rest} = props
    const { register, handleSubmit, formState: { errors } } = useForm();
  return (
        <Modal open={openModal} setOpen={setOpenModal}>
            <div className='contenedor-centrado'>
            <H1>{titulo}</H1>
            <form onSubmit={handleSubmit(onSubmit)} className="modal-form">
                {campos.map((campo,index)=>
                campo.tipo!="custom"?
                <InputFormText key={index}
                    checkOrRadioValues={campo.values}
                    type={campo.tipo}
                    register={register}
                    nombreLabel={campo.nombreForm}
                    nombre={campo.nombre}
                    placeholder={campo.placeHolder}
                    errors={errors}
                />:null
                )}
                {children}
              <Button type="submit" onClick={handleSubmit}>
                Enviar
              </Button>
            </form>
            </div>
        </Modal>
  )
}

export default ModalForm