import React, {useEffect} from 'react';
import Loadable from 'react-loadable'
import history from "../../routes/history";

const SafeRouter = ({component}) => {

    useEffect(() => {
        let token = localStorage.getItem("token");
        if (token == null) {
            history.push('/login');
        }
    });

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


export default SafeRouter;