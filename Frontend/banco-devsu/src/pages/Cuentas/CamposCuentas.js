
const CamposCuentas = [
    {
      nombre:"tipo" ,
      nombreForm: "tipo",
      placeHolder: "ahorro",
      tipo: "radio",
      values:["ahorro","corriente"]
    },
    {
      nombre:"saldo",
      nombreForm: "saldo",
      tipo: "number",
      placeHolder: "Erick Vaernet"
    },
    {
      nombre:"estado" ,
      nombreForm: "estado",
      tipo: "radio",
      values:["true","false"]
    },
    {
      nombre:"cliente" ,
      nombreForm: "cliente",
      subpropGet:"nombres",
      subpropRequest:"id",
      tipo: "select",
      placeHolder: "Juan Perez"
    }
  ];

export default CamposCuentas;
