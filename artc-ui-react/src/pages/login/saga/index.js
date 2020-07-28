import Constants from "../constants";
import {take, call, put} from 'redux-saga/effects'
import axios from 'axios';

function* loginAuthorize(username, password) {
    try {
        const token = yield call(axios.post, "/login", {loginName: username, password: password});
        yield put({type: Constants.LOGIN_SUCCESS, token});
        return token;
    } catch (error) {
        yield put({type: Constants.LOGIN_FAIL, error})
    }
}

function* login() {
    while (true) {
        const {username, password} = yield take(Constants.LOGIN_REQUEST);
        const token = yield call(loginAuthorize, username, password);
        if (token) {
            localStorage.setItem("token", token);
        }
    }
}

const LoginSagas = [
    login()
]

export default LoginSagas;