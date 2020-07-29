import * as LoginReducers from '../pages/login/reducer';
import * as MenuReducers from '../pages/sys/menu/reducer';

const Reducers = {
    ...LoginReducers,
    ...MenuReducers
}

export default Reducers;