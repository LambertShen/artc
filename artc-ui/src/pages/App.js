import React, {Suspense, lazy} from 'react';
import {BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom'
import Container from "../components/container";
import NotFound from "./404";
import Login from "./login";
import Test from "./test";

const routers = [
    {path: "/", name: "test", component: './test'}
]

class App extends React.Component {

    render() {
        let token = localStorage.getItem("token");
        return (
            <Router>
                <Switch>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/">
                        <Container>
                            <Route exact path="/" component={Test}/>
                        </Container>
                    </Route>
                    {/*<Switch>*/}
                    {/*    {*/}
                    {/*        routers.map((item, index) => {*/}
                    {/*            const DynamicComponent = lazy(() => import(`${item.component}/index`));*/}
                    {/*            return (*/}
                    {/*                <Route key={index} path={item.path} exact render={props => (*/}
                    {/*                    <Container>*/}
                    {/*                        <Suspense fallback={<div>loading...</div>}>*/}
                    {/*                            {*/}
                    {/*                                token ? <DynamicComponent/> :*/}
                    {/*                                    <Redirect to={{pathname: "/login"}}/>*/}
                    {/*                            }*/}
                    {/*                        </Suspense>*/}
                    {/*                    </Container>*/}
                    {/*                )}/>*/}
                    {/*            )*/}
                    {/*        })*/}
                    {/*    }*/}
                    {/*</Switch>*/}
                    <Route component={NotFound}/>
                </Switch>
            </Router>
        )
    }
}

export default App;