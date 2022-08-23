/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function GetClientes(setProducto) {
  axios;
  instanceApi
    .get("/clientes?pagina=0")
    .then(({ data: response }) => {
      const { resultados } = response;
      setProducto(resultados);
    })
    .catch((error) => {
      throw new Error(`Los clientes no fueron retornados correctamente: ${error}`);
    });
}

export { GetClientes };
