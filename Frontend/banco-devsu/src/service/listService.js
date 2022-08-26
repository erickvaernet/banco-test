/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function listService(uri, page) {
  if (page < 1) page = 1;
  axios;
  return instanceApi
    .get(`${uri}?pagina=${page - 1}`)
    .then(({ data: response }) => {
      return response;
    })
    .catch((error) => {
      throw new Error(
        `Los ${uri} no fueron retornados correctamente: ${error}`
      );
    });
}

export { listService };
