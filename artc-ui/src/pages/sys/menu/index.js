import React from 'react';
import './index.less';
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {Table, Card, Button, Tooltip, Tag, Modal} from 'antd';
import {
    PlusOutlined,
    LineOutlined,
    EditOutlined,
    ArrowUpOutlined,
    ArrowDownOutlined,
    FullscreenOutlined,
    ReloadOutlined,
    SettingOutlined
} from '@ant-design/icons'
import Form from "./components/form";
import axios from "axios";

class Menu extends React.Component {

    state = {
        form: {
            title: "菜单管理",
            visible: false,
            treeSelect: [],
            handleCloseForm: () => {
                this.setState({form: {...this.state.form, visible: false}})
            },
            handlerSubmitForm: () => {
                this.init();
                this.setState({form: {...this.state.form, visible: false}})
            }
        },
        data: []
    }

    componentDidMount() {
        this.init();
    }

    init = () => {
        const self = this;
        axios.get(
            "/core/menu",
        ).then((response) => {
            console.log(response)
            let treeSelect = self.getTreeSelect(response.data);
            self.setState({
                data: response.data,
                form: {...self.state.form, treeSelect: treeSelect}
            })
        })
    }

    getTreeSelect = (menus) => {
        let result = [];
        if (menus == null) return null;
        for (let i = 0; i < menus.length; i++) {
            let menu = menus[i];
            if (menu.type === 1) continue;
            let node = {value: menu.id, title: menu.name};
            let childrenMenus = this.getTreeSelect(menu.children);
            if (childrenMenus != null) {
                node.children = childrenMenus;
            }
            result.push(node);
        }
        return result;
    }

    asort = (record, index, move) => {
        let data = this.state.data;
        return false
    }

    findMenuGroup = (data, parentId) => {
        if (parentId === "0") {
            return data;
        }
        data.forEach(item => {
            if (item.id === parentId) {
                return item.children;
            }
            return this.findMenuGroup(item, parentId);
        })
        return [];
    }

    columns = [
        {
            title: '名称',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: '排序',
            dataIndex: 'sort',
            key: 'sort',
            render(text, record, index) {
                const [modal, contextHolder] = Modal.useModal();
                return (
                    <div className="sort">
                        <ArrowUpOutlined onClick={() => {
                            // let result = this.sort(record, index, 0)
                            // if (!result) {
                            //     modal.confirm({title: 'hello', content: (<div>hello</div>)})
                            // }
                        }}/>
                        <ArrowDownOutlined/>
                        {contextHolder}
                    </div>
                )
            }
        },
        {
            title: '类型',
            dataIndex: 'type',
            key: 'type',
            width: '12%',
            render(type) {
                return (
                    <Tag color={type === 0 ? 'red' : 'green'} key={type}>
                        {type === 0 ? '菜单' : '按钮'}
                    </Tag>
                )
            }
        },
        {
            title: '访问路径',
            dataIndex: 'url',
            key: 'url',
            width: '12%',
        },
        {
            title: '权限标识',
            dataIndex: 'permission',
            width: '15%',
            key: 'permission',
        },
        {
            title: '操作',
            dataIndex: 'action',
            width: '20%',
            key: 'action',
            render() {
                return (
                    <div className="action">
                        <Button type="primary"
                                style={{color: '#13c2c2', backgroundColor: '#13c2c2', borderColor: '#13c2c2'}} ghost
                                size="small">查看</Button>
                        <Button type="primary"
                                style={{color: '#52c41a', backgroundColor: '#52c41a', borderColor: '#52c41a'}} ghost
                                size="small">编辑</Button>
                        <Button type="primary" danger ghost size="small">删除</Button>
                    </div>
                )
            }
        },
    ];

    render() {
        return (
            <>
                <Form {...this.state.form}/>
                <Card bodyStyle={{padding: 0}}>
                    <div className="table-toolbar">
                        <div className="table-toolbar-title">菜单管理</div>
                        <div className="table-toolbar-option">
                            <div className="table-toolbar-option-button-group">
                                <Button icon={<PlusOutlined/>} onClick={() => {
                                    this.setState({form: {...this.state.form, visible: true}})
                                }} type="primary">新增</Button>
                                {/*<Button icon={<EditOutlined/>} type="primary"*/}
                                {/*        style={{backgroundColor: '#52c41a', borderColor: '#52c41a'}}>编辑</Button>*/}
                                {/*<Button icon={<LineOutlined/>} type="primary" danger>删除</Button>*/}
                            </div>
                            <div className="table-toolbar-option-default">
                                <Tooltip placement="top" title="全屏">
                                    <FullscreenOutlined/>
                                </Tooltip>
                                <Tooltip placement="top" title="刷新">
                                    <ReloadOutlined/>
                                </Tooltip>
                                <Tooltip placement="top" title="列设置">
                                    <SettingOutlined/>
                                </Tooltip>
                            </div>
                        </div>
                    </div>
                    <Table
                        columns={this.columns}
                        dataSource={this.state.data}
                        pagination={{hideOnSinglePage: true}}
                        test="aaa"
                    />
                </Card>
            </>
        );
    }

}

function mapStateToProps(state) {
    return {}
}

function mapDispatchToProps(dispatch) {
    return {}
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Menu));