/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function getByIdService(uri,id) {
  axios;
  return instanceApi
    .get(`${uri}/${id}`)
    .then(({ data: response }) => {
      return response;
    })
    .catch((error) => {
      throw new Error(`Los ${uri} no fueron retornados correctamente: ${error}`);
    });
}

export { getByIdService };