/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function deleteByIdService(uri,id) {
  axios;
  return instanceApi
    .delete(`${uri}/${id}`)
    .then(({ data: response }) => {
        console.log(response.status);
      return response;
    })
    .catch((error) => {
      throw new Error(`No se pudo eliminar el elemento deseado`);
    });
}

export { deleteByIdService };