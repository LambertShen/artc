import React from 'react';
import Loadable from 'react-loadable'
import {withRouter} from "react-router-dom";


class SafeRouter extends React.Component {

    componentDidMount() {
        let token = localStorage.getItem("token");
        if (token == null) {
            this.props.history.push('/login');
        }
    }

    render() {
        const {component} = this.props;
        const LoadableComponent = Loadable({
            loader: () => import(`../../pages/${component}`),
            loading: () => (
                <span>loading...</span>
            )
        })
        return (
            <LoadableComponent/>
        )
    }
}


export default withRouter(SafeRouter);