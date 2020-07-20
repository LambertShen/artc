import Constants from "../constants";

export const login = loginInfo => {
    if (loginInfo.success) {
        localStorage.setItem("token", loginInfo.data.token);
    }
    return {
        type: Constants.LOGIN,
        data: loginInfo
    }
}
