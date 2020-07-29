import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import './index.less';
import {Table, Card, Button, Tooltip, Tag, Modal} from 'antd';
import {
    PlusOutlined,
    FullscreenOutlined,
    ReloadOutlined,
    SettingOutlined,
    ArrowUpOutlined,
    ArrowDownOutlined
} from '@ant-design/icons'
import Form from "./form";
import {list_action, setVisibleMode_action} from "./action";


const Menu = (props) => {

    const {list, dataSource, setVisible, loading} = props;
    useEffect(()=>{
        list();
    }, [list])

    const sort = (record, index, move) => {
        alert("sort");
    }

    const columns = [
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
                           sort(record, index, 0)
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
            render(text, record, index) {
                return (
                    <div className="action">
                        <Button type="primary"
                                onClick={() => setVisible(true, "view", record.id)}
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

    return (
        <>
            <Form/>
            <Card bodyStyle={{padding: 0}}>
                <div className="table-toolbar">
                    <div className="table-toolbar-title">菜单管理</div>
                    <div className="table-toolbar-option">
                        <div className="table-toolbar-option-button-group">
                            <Button icon={<PlusOutlined/>} type="primary" onClick={() => setVisible(true)}>新增</Button>
                        </div>
                        <div className="table-toolbar-option-default">
                            <Tooltip placement="top" title="全屏">
                                <FullscreenOutlined/>
                            </Tooltip>
                            <Tooltip placement="top" title="刷新">
                                <ReloadOutlined onClick={() => list()}/>
                            </Tooltip>
                            <Tooltip placement="top" title="列设置">
                                <SettingOutlined/>
                            </Tooltip>
                        </div>
                    </div>
                </div>
                <Table
                    loading={loading}
                    columns={columns}
                    dataSource={dataSource}
                    pagination={{hideOnSinglePage: true}}
                />
            </Card>
        </>
    )
}

function mapStateToProps(state) {
    return {
        dataSource: state.menuInfo.dataSource,
        loading: state.menuInfo.loading
    }
}

function mapDispatchToProps(dispatch) {
    return {
        list: () => dispatch(list_action()),
        setVisible: (visible, mode, id) => dispatch(setVisibleMode_action(visible, mode, id))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Menu);