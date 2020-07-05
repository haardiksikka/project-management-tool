import { GET_ERRORS } from "../actions/type";

var inititalState = {};

export function errorReducer(state = inititalState, action) {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;
    default:
      return state;
  }
}
