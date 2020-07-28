import Constants from "../constants";

export const login_action = (username, password) => ({
    type: Constants.LOGIN_REQUEST,
    username: username,
    password: password
})