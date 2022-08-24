/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function listService(uri,page) {
  axios;
  instanceApi
    .get(`/${uri}?pagina=${page}`)
    .then(({ data: response }) => {
      const { resultados } = response;
      return resultado;
    })
    .catch((error) => {
      throw new Error(`Los ${uri} no fueron retornados correctamente: ${error}`);
    });
}

export { listService };
