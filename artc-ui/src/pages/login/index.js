import React from 'react';
import './index.css';
import {UserOutlined, KeyOutlined} from '@ant-design/icons'
import {Button} from "antd";
import {login} from './action'
import {connect} from 'react-redux'
import axios from "axios";

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.username = React.createRef();
        this.password = React.createRef();
    }

    submit() {
        const {username, password} = this;
        const {login} = this.props;
        login(username.current.value, password.current.value);
    }

    render() {
        console.log(this.props)
        return (
            <div className="login-background">
                <div className="login-box">
                    <h1>登录</h1>
                    <div className="form">
                        <div className="item">
                            <UserOutlined className="icon"/>
                            <input type="text"
                                   ref={this.username}
                                   placeholder="用户名"
                            />
                        </div>
                        <div className="item">
                            <KeyOutlined className="icon"/>
                            <input type="password"
                                   ref={this.password}
                                   placeholder="密码"
                            />
                        </div>
                    </div>
                    <Button type="primary" onClick={() => this.submit()}>登录</Button>
                </div>
            </div>
        )
    }

}

function mapStateToProps(state) {
    return {
        loginInfo: state.loginInfo
    }
}

function mapDispatchToProps(dispatch) {
    return {
        login: (username, password) => loginDispatch(username, password, dispatch)
    }
}

function loginDispatch(username, password, dispatch) {
    axios.post(
        "/login",
        {
            loginName: username,
            password: password
        }
    ).then(response => {
        dispatch(
            login(response)
        )
    })
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
