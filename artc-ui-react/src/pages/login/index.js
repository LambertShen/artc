import React, {useEffect} from 'react';
import {useHistory} from "react-router-dom";
import './index.less';
import {login_action} from './action';
import {connect} from "react-redux";

const Login = ({login, success, error}) => {

    const history = useHistory();

    let username, password;

    useEffect(() => {
        if(success) {
            let token = localStorage.getItem("token");
            if (token) history.push("/");
        }
    })

    const submit = () => {
        login(username.value, password.value);
    }

    return (
        <div className="container">
            <div className="form-container">
                <div className="form-body">
                    <div className="header">
                        <h1>登录</h1>
                    </div>
                    <div className="form-group">
                        <div className="input-group">
                            <input type="text" placeholder="用户名" ref={input => username = input}/>
                        </div>
                        <div className="input-group">
                            <input type="password" placeholder="密码" ref={input => password = input}/>
                        </div>
                        <div className="input-group right">
                            <button onClick={() => submit()}>登录</button>
                        </div>
                    </div>
                </div>
                <div className="form-image">
                    <div className="text">
                        <h1>Art Code</h1>
                        <h2>欢迎使用！</h2>
                        <p>致力于研发自动化办公解决方案，感谢您的使用，您的支持都是我们研发的动力！</p>
                    </div>
                </div>
            </div>
        </div>
    )
}


function mapStateToProps(state) {
    return {
        ...state.loginInfo
    }
}

function mapDispatchToProps(dispatch) {
    return {
        login: (username, password) => dispatch(login_action(username, password))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
