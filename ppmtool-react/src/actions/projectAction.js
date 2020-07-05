//use for sending post requests.
//can make wrapper for sending http requests

import axios from "axios";
import { GET_ERRORS } from "./type";

var url = "http://localhost:8087/api/project";

export const createProject = (project, history) => async (dispatch) => {
  try {
    //axios returns a promise object
    const res = await axios.post(url, project);

    //on success redirect to dashboard
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
