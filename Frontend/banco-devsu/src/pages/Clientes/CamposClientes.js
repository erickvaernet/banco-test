
const camposClientes = [
    {
      nombre: "identificacion",
      nombreForm: "Identificacion",
      placeHolder: "45245777",
      tipo: "text"        
    },
    {
      nombre: "nombres",
      nombreForm: "Nombres",
      tipo: "text",
      placeHolder: "Erick Vaernet"        
    },
    {
      nombre: "genero",
      nombreForm: "Genero",
      tipo: "radio",
      values:["Masculino","Femenino","Otro"]
    },
    {
      nombre: "fechaNacimiento",
      nombreForm: "Fecha de Nacimiento",
      tipo: "text",
      placeHolder: "año-mes-dia ej: 1996-09-31"
    },
    {
      nombre: "edad",
      nombreForm: "Edad",
      tipo: "number",
      placeHolder: 21    
    },
    {
      nombre: "direccion",
      nombreForm: "Direccion",
      tipo: "text",
      placeHolder: "Avenida Alvear 380"
    },
    {
      nombre: "telefono",
      nombreForm: "Telefono",
      tipo: "text",
      placeHolder: "+5493624284819"
    },
    {
      nombre: "contrasenia",
      nombreForm: "Contraseña",
      tipo: "password",
      placeHolder: "******"        
    },
    {
      nombre: "estado",
      nombreForm: "Estado",
      tipo: "radio",
      values:["true","false"]
    },
  ];

export default camposClientes;
