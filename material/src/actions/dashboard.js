import {HTTP} from './../utils/HTTP';

import {
  DASHBOARD_STATBOXES_GET_FAIL,
  DASHBOARD_STATBOXES_GET_REQUEST,
  DASHBOARD_STATBOXES_GET_SUCCESS,
  PROGRESS_ADD_REQUEST,
  PROGRESS_REMOVE_REQUEST
} from './../constants/ActionTypes';

export function dashboardStatBoxesGetRequest() {
  return function (dispatch) {
    return new Promise(function(resolve, reject){{

      // will be removed once API is ready
      // dispatch({
      //   type: DASHBOARD_STATBOXES_GET_REQUEST,
      //   data: {
      //   }
      // });
      // resolve(true);
      // return;

      dispatch({
        type: PROGRESS_ADD_REQUEST
      });
      // API
      HTTP('get', '/dashboard/', null,{authorization: localStorage.getItem('@fnovella:token')})
        .then(function (response) {
          if(response.data.errors===null){
            dispatch({
              type: DASHBOARD_STATBOXES_GET_SUCCESS,
              data: response.data.data
            });
            resolve(response.data);
          }else {
            reject(response.data);
          }
        })
        .catch(error => {
          dispatch({
            type: DASHBOARD_STATBOXES_GET_FAIL,
            error: error
          });
          reject(error);
        })
        .finally(()=>{
          dispatch({
            type: PROGRESS_REMOVE_REQUEST
          });
        })
    }})
  }
}
