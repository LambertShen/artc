import {createStore, combineReducers} from "redux";
import Reducers from "../reducer";

const storeFactory = () => {
    let cache = localStorage.getItem('artc-cache');
    return createStore(
        combineReducers({...Reducers}),
        cache ? JSON.parse(cache) : {}
    )
}

export default storeFactory;