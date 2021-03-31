import axios from "axios";

const axiosUtil = {
  get: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .get(url, data, {
        headers: { "Content-Type": "Application/json" },
      })
      .then(cbSuccess)
      .catch(cbFail)
      .then(cbComplete);
  },

  post: function (url, data, cbSuccess, cbFail, cbComplete) {
    axios
      .post(url, data, {
        headers: { "Content-Type": "Application/json" },
      })
      .then(cbSuccess)
      .catch(cbFail)
      .then(cbComplete);
  },
};

export default axiosUtil;
