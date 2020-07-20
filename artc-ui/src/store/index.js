import {createStore, combineReducers, applyMiddleware} from "redux";
import Reducers from "../reducer";

const saver = store => next => action => {
    next(action);
    localStorage.setItem('artc-cache', JSON.stringify(store.getState()));
}

const storeFactory = () => {
    let cache = localStorage.getItem('artc-cache');
    return createStore(
        combineReducers({...Reducers}),
        cache ? JSON.parse(cache) : {},
        applyMiddleware(saver)
    )
}

export default storeFactory;