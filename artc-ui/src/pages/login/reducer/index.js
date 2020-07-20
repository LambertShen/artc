import Constants from "../constants";

export const loginInfo = (state = {}, action) => {
    switch (action.type) {
        case Constants.LOGIN:
            return action.data;
        default:
            return state;
    }
}