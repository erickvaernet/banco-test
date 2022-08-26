
const CamposMovimientos =[
    {
      nombre: "fecha",
      nombreForm: "Fecha",
      tipo: "text",
      placeHolder: "año-mes-dia ej: 1996-09-31"
    },
    {
      nombre: "valor",
      nombreForm: "Valor",
      tipo: "number",
      placeHolder: "1600.60"
    },
    {
      nombre: "tipo",
      nombreForm: "Tipo",
      tipo: "radio",
      values:["deposito","retiro"]
    },
    {
      nombre: "cuenta",
      nombreForm: "Cuenta Destino",
      tipo: "custom",
      placeHolder: "3123"
    }
  ];

export default CamposMovimientos;
