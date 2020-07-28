import React from 'react';
import './index.less';
import {Layout as AntLayout} from 'antd';
import {
    MenuUnfoldOutlined,
    MenuFoldOutlined,
} from '@ant-design/icons';
import Menu from "./components/menu";

const {Header, Sider, Content} = AntLayout;

class Container extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            collapsed: false,
        }
    }

    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    };

    render() {
        return (
            <AntLayout className="site" style={{minHeight: '100vh'}}>
                <Sider collapsedWidth={60} theme="light" trigger={null} collapsible collapsed={this.state.collapsed} className="site-left">
                    <div className="logo">
                        <a href="/">
                            <img src={[require("../asset/icons/art-logo.png")]} alt="logo"/>
                            {this.state.collapsed || <h1>Art Code</h1>}
                        </a>
                    </div>
                    <Menu/>
                </Sider>
                <AntLayout className="site-right">
                    <Header className="header">
                        <div className="global-header">
                            {React.createElement(this.state.collapsed ? MenuUnfoldOutlined : MenuFoldOutlined, {
                                className: 'trigger',
                                onClick: this.toggle,
                            })}
                        </div>
                    </Header>
                    <Content className="content">
                        {this.props.children}
                    </Content>
                </AntLayout>
            </AntLayout>
        );
    }
}

export default Container;