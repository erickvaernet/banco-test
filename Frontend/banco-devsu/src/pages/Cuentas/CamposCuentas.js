
const CamposCuentas = [
    {
      nombre:"tipo" ,
      nombreForm: "tipo",
      placeHolder: "ahorro",
      tipo: "radio",
      values:["ahorro","corriente"],
    },
    {
      nombre:"saldo",
      nombreForm: "saldo",
      tipo: "number",
      placeHolder: "1600.50",
    },
    {
      nombre:"estado" ,
      nombreForm: "estado",
      tipo: "radio",
      values:["true","false"],
    },
    {
      nombre:"cliente" ,
      nombreForm: "cliente",
      tipo: "custom",
    }
  ];

export default CamposCuentas;
