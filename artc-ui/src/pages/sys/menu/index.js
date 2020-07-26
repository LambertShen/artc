import React from 'react';
import './index.less';
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {Table, Card, Button, Tooltip} from 'antd';
import {
    PlusOutlined,
    LineOutlined,
    EditOutlined,
    FullscreenOutlined,
    ReloadOutlined,
    SettingOutlined
} from '@ant-design/icons'
import Form from "./components/form";

class Menu extends React.Component {

    state = {
        form: {
            title: "菜单管理",
            visible: false,
            handleCloseForm: () => {
                this.setState({form: {...this.state.form, visible: false}})
            },
            handlerSubmitForm: () => {
                this.setState({form: {...this.state.form, visible: false}})
            }
        }
    }

    columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'Age',
            dataIndex: 'age',
            key: 'age',
            width: '12%',
        },
        {
            title: 'Address',
            dataIndex: 'address',
            width: '30%',
            key: 'address',
        },
    ];

    data = [];

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
                                <Button icon={<EditOutlined/>} type="primary"
                                        style={{backgroundColor: '#52c41a', borderColor: '#52c41a'}}>编辑</Button>
                                <Button icon={<LineOutlined/>} type="primary" danger>删除</Button>
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
                        dataSource={this.data}
                        pagination={{hideOnSinglePage: true}}
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