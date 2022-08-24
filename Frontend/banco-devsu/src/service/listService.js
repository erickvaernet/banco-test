/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function listService(uri,page) {
  axios;
  return instanceApi
    .get(`/${uri}?numeroPagina=${page}`)
    .then(({ data: response }) => {
      const { resultados } = response;
      return resultados;
    })
    .catch((error) => {
      throw new Error(`Los ${uri} no fueron retornados correctamente: ${error}`);
    });
}

export { listService };
