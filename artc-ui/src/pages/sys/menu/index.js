import React from 'react';
import './index.less';
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import { Table, Switch, Space } from 'antd';

class Menu extends React.Component {

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

    data = [
        {
            key: 1,
            name: 'John Brown sr.',
            age: 60,
            address: 'New York No. 1 Lake Park',
            children: [
                {
                    key: 11,
                    name: 'John Brown',
                    age: 42,
                    address: 'New York No. 2 Lake Park',
                },
                {
                    key: 12,
                    name: 'John Brown jr.',
                    age: 30,
                    address: 'New York No. 3 Lake Park',
                    children: [
                        {
                            key: 121,
                            name: 'Jimmy Brown',
                            age: 16,
                            address: 'New York No. 3 Lake Park',
                        },
                    ],
                },
                {
                    key: 13,
                    name: 'Jim Green sr.',
                    age: 72,
                    address: 'London No. 1 Lake Park',
                    children: [
                        {
                            key: 131,
                            name: 'Jim Green',
                            age: 42,
                            address: 'London No. 2 Lake Park',
                            children: [
                                {
                                    key: 1311,
                                    name: 'Jim Green jr.',
                                    age: 25,
                                    address: 'London No. 3 Lake Park',
                                },
                                {
                                    key: 1312,
                                    name: 'Jimmy Green sr.',
                                    age: 18,
                                    address: 'London No. 4 Lake Park',
                                },
                            ],
                        },
                    ],
                },
            ],
        },
        {
            key: 2,
            name: 'Joe Black',
            age: 32,
            address: 'Sidney No. 1 Lake Park',
        },
    ];

    render() {
        return (
            <>
                <Space align="center" style={{ marginBottom: 16 }}>
                </Space>
                <Table
                    columns={this.columns}
                    dataSource={this.data}
                    pagination={{hideOnSinglePage: true}}
                />
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