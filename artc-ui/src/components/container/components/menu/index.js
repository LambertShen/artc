import React from 'react';
import {Menu as AntMenu} from 'antd';
import {SettingOutlined} from '@ant-design/icons';
const {SubMenu, Item} = AntMenu

class Menu extends React.Component {

    render() {
        return (
            <AntMenu inlineIndent="16" theme="light" mode="inline" defaultSelectedKeys={['1']}>
                <SubMenu icon={<SettingOutlined />} title="系统设置">
                    <Item>菜单管理</Item>
                </SubMenu>
            </AntMenu>
        )
    }

}

export default Menu;