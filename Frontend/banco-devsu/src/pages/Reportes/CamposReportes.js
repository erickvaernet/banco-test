
const CamposReportes = [
    {
      nombre: "fecha",
      nombreForm: "Fecha",
      placeHolder: "45245777",
      tipo: "text"        
    },
    {
      nombre: "nombresCliente",
      nombreForm: "Nombres del cliente",
      tipo: "text",
      placeHolder: "Erick Vaernet"        
    },
    {
      nombre: "numeroCuenta",
      nombreForm: "Numero de cuenta",
      tipo: "radio",
      values:["Masculino","Femenino","Otro"]
    },
    {
      nombre: "tipoCuenta",
      nombreForm: "Tipo de cuenta",
      tipo: "number",
      placeHolder: 21    
    },
    {
      nombre: "saldoInicial",
      nombreForm: "Saldo inicial",
      tipo: "text",
      placeHolder: "Avenida Alvear 380"
    },
    {
      nombre: "tipoMovimiento",
      nombreForm: "Tipo movimiento",
      tipo: "password",
      placeHolder: "******"        
    },{
      nombre: "valor",
      nombreForm: "Valor",
      tipo: "radio",
      values:["true","false"]
    },
    {
      nombre: "saldoDisponible",
      nombreForm: "Saldo disponible",
      tipo: "radio",
      values:["true","false"]
    },
    {
      nombre: "estado",
      nombreForm: "Estado",
      tipo: "text",
      placeHolder: "+5493624284819"
    },
    
  ];

export default CamposReportes;
