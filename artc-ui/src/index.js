import React from 'react';
import ReactDOM from 'react-dom';
import App from "./pages/App";
import {Provider} from 'react-redux';
import storeFactory from "./store";
import './config/axios-config'
import './index.less';

let store = storeFactory();

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);