import axios from 'axios';

function login(username, password) {
    axios.post(
        "login",
        {
            loginName: username,
            password: password
        }
    )
}

const Api = {
    login
}

export default Api;