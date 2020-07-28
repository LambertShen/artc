import {createStore, combineReducers, applyMiddleware} from "redux";
import Reducers from "./reducers";
import rootSagas from "./sagas";
import createSagaMiddleware from 'redux-saga'


const sagaMiddleware = createSagaMiddleware();

const store = createStore(
    combineReducers({...Reducers}),
    applyMiddleware(sagaMiddleware)
)

sagaMiddleware.run(rootSagas);

export default store;