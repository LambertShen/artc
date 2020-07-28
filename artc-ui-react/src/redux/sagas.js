import LoginSagas from '../pages/login/saga';
import {all} from 'redux-saga/effects'

function* rootSagas() {
    yield all([
        ...LoginSagas
    ])
}

export default rootSagas;