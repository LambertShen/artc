import React, {useEffect} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Loadable from "react-loadable";
import Container from "../container";
import Login from "../pages/login";
import NotFound from "../pages/not-found";
import {useHistory} from "react-router-dom";

const Routes = () => {

    const routers = [
        {path: "/", component: 'home'},
        {path: "/menu", component: 'sys/menu'}
    ]

    return (
        <Router>
            <Switch>
                <Route exact path="/login" component={Login}/>
                <Container>
                    {
                        routers.map((route, index) =>
                            <Route key={index} exact path={route.path} render={props => (
                                <SafeRoute {...route}/>
                            )}/>
                        )
                    }
                </Container>
                <Route component={NotFound}/>
            </Switch>
        </Router>
    )

}

const SafeRoute = ({component}) => {

    const history = useHistory();

    useEffect(() => {
        let token = localStorage.getItem("token");
        if (token == null) {
            history.push('/login');
        }
    });

    const LoadableComponent = Loadable({
        loader: () => import(`../pages/${component}`),
        loading: () => (
            <span>loading...</span>
        )
    })

    return (
        <LoadableComponent/>
    )
}

export default Routes;