import Constants from "../constants";

export const loginInfo = (state = {}, action) => {
    switch (action.type) {
        case Constants.LOGIN_SUCCESS:
            return {
                success: true,
            }
        case Constants.LOGIN_FAIL:
            return {
                success: false,
                info: action.error
            }
        default:
            return state;
    }
}