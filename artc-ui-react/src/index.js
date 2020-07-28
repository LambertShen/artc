import React from 'react';
import ReactDOM from 'react-dom';
import './index.less';
import Routes from "./routes";
import {Provider} from 'react-redux';
import store from "./redux/store";
import './config/axios-config'

ReactDOM.render(
    <Provider store={store}>
        <Routes/>
    </Provider>,
    document.getElementById('root')
);
