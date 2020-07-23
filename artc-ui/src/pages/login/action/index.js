import Constants from "../constants";

export const login = loginInfo => {

    return {
        type: Constants.LOGIN,
        data: loginInfo
    }

}
