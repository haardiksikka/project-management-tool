//use for sending post requests.
//can make wrapper for sending http requests

import axios from "axios";
import { GET_ERRORS, GET_PROJECT, DELETE_PROJECT } from "./type";
import { GET_PROJECTS } from "./type";

var url = "/api/project";

export const createProject = (project, history) => async (dispatch) => {
  try {
    //axios returns a promise object
    const res = await axios.post(url, project);
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
    //on success redirect to dashboard
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  try {
    //send request
    const res = await axios.get(url + "/all");
    dispatch({
      type: GET_PROJECTS,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getProject = (id, history) => async (dispatch) => {
  try {
    const res = await axios.get(url + `/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data,
    });

    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    history.push("/dashboard");
  }
};

export const deleteProject = (id) => async (dispatch) => {
  if (window.confirm("Are you sure, you want to delete project?")) {
    const res = await axios.delete(url + `/${id}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: id,
    });
  }
  //update state to remove
};
