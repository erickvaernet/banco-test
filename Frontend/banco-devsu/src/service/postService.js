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
        const errorM= error.response.data.message? error.response.data.message:error
        throw new Error(`${errorM}`);
    });
}

export { postService };