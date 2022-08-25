
const CamposMovimientos = {
  response:[
    {
      nombre: "valor",
      nombreForm: "valor",
      tipo: "number",
      placeHolder: "1000.00"        
    },
    {
      nombre: "numeroCuenta",
      nombreForm: "Numero de Cuenta",
      tipo: "number",
      placeHolder: "3123"
    },
    {
      nombre: "tipo",
      nombreForm: "tipo",
      tipo: "radio",
      values:["deposito","retiro"]
    }
  ],
  request:[
    {
      nombre: "fecha",
      nombreForm: "fecha",
    },
    ...response
  ]
};

export default CamposMovimientos;
