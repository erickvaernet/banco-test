/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function postService(uri,data) {
    const cuerpo = data;
    axios;
    return instanceApi
    .post(`${uri}`,cuerpo)
    .then(({ data: response }) => {
        return response
    })
    .catch((error) => {
        throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

export { postService };