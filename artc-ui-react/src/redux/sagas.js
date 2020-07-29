import LoginSagas from '../pages/login/saga';
import MenuSagas from "../pages/sys/menu/saga";
import {all} from 'redux-saga/effects'

function* rootSagas() {
    yield all([
        ...LoginSagas,
        ...MenuSagas
    ])
}

export default rootSagas;