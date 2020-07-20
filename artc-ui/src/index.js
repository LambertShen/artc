import React from 'react';
import ReactDOM from 'react-dom';
import App from "./pages/App";

import {
    BrowserRouter as Router
} from "react-router-dom";
import {Provider} from 'react-redux';
import storeFactory from "./store";
import './config/axios-config'
import './index.less';

let store = storeFactory();

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <App/>
        </Router>
    </Provider>,
    document.getElementById('root')
);