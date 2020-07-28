import axios from 'axios';

const ACCESS_TOKEN = "Access-Token"

axios.defaults.baseURL = '/api';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

axios.interceptors.request.use(function (config) {
    let token = localStorage.getItem("token");
    if(token != null) {
        config.headers.common[ACCESS_TOKEN] = token;
    }
    return config;
}, function (error) {
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
    if(response.data.fail) {
        return Promise.reject(response.data.message);
    }
    return response.data.data;
}, function (error) {
    return Promise.reject(error);
});