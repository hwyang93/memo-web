import axios from 'axios';
import store from '../store/index.js';
import router from '../router/index.js';

const axiosUtil = {
  get: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .get(url, {
        headers: {
          token: store.state.auth.userInfo.token
        },
        params: data
      })
      .then(cbSuccess)
      .catch((result) => {
          debugger;
          console.log(result);
        router.push('/login');
      })
      .then(cbComplete);
  },

  post: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .post(url, data, {
        headers: {
          'Content-Type': 'Application/json',
          token: store.state.auth.userInfo.token
        }
      })
      .then(cbSuccess)
      .catch(()=>{
          router.push('/login');
      })
      .then(cbComplete);
  },

  put: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .put(url, data, {
        headers: {
            'Content-Type': 'Application/json',
            token: store.state.auth.userInfo.token
        }
      })
      .then(cbSuccess)
      .catch(cbFail)
      .then(cbComplete);
  }

  // delete: function (url, data, cbSuccess, cbFail, cbComplete) {
  //   axios
  //     .delete(url, data, {
  //       headers: { 'Content-Type': 'Application/json' }
  //     })
  //     .then(cbSuccess)
  //     .catch(cbFail)
  //     .then(cbComplete);
  // }

  // get: function (url, data, cbSuccess, cbFail, cbComplete) {
  //   debugger;
  //   axios({
  //     method: 'get',
  //     url: url,
  //     headers: { 'Content-Type': 'Application/json' },
  //     data: { keyword: 't' }
  //   }).then(cbSuccess);
  // },

  // post: function (url, data, cbSuccess, cbFail, cbComplete) {
  //   axios({
  //     method: 'post',
  //     url: url,
  //     headers: { 'Content-Type': 'Application/json' },
  //     data: data
  //   }).then(cbSuccess);
  // },

  // put: function (url, data, cbSuccess, cbFail, cbComplete) {
  //   axios({
  //     method: 'put',
  //     url: url,
  //     headers: { 'Content-Type': 'Application/json' },
  //     data: data
  //   }).then(cbSuccess);
  // },

  // delete: function (url, data, cbSuccess, cbFail, cbComplete) {
  //   axios({
  //     method: 'delete',
  //     url: url,
  //     headers: { 'Content-Type': 'Application/json' },
  //     data: data
  //   }).then(cbSuccess);
  // }
};

export default axiosUtil;
