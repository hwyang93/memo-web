import axios from 'axios';

const axiosUtil = {
  get: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios.get(url, { params: data }).then(cbSuccess).catch(cbFail).then(cbComplete);
  },

  post: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .post(url, data, {
        headers: { 'Content-Type': 'Application/json' }
      })
      .then(cbSuccess)
      .catch(cbFail)
      .then(cbComplete);
  },

  put: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .put(url, data, {
        headers: { 'Content-Type': 'Application/json' }
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
