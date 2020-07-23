import React from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Container from "../components/container";
import NotFound from "./404";
import Login from "./login";
import SafeRouter from "../components/safe-router";

const routers = [
    {path: "/", component: 'home'},
    {path: "/menu", component: 'sys/menu'}
]

class App extends React.Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route exact path="/login" component={Login}/>
                    <Container>
                        {
                            routers.map((route, index) =>
                                <Route key={index} exact path={route.path} render={props => (
                                    <SafeRouter {...route}/>
                                )}/>
                            )
                        }
                    </Container>
                    <Route component={NotFound}/>
                </Switch>
            </Router>
        )
    }
}

export default App;