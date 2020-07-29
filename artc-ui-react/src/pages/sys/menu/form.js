import React, {useEffect, useState} from 'react';
import {Modal, Form as AntForm, Input, Radio, Button, TreeSelect} from "antd";
import {connect} from 'react-redux';
import {add_action, setVisibleMode_action} from "./action";

const Form = (props) => {

    const {treeData, visible, setVisible, loading, data, add} = props;

    const [form] = AntForm.useForm();

    useEffect(() => {
        if(loading !== undefined && !loading) {
            form.resetFields(['name', 'url', 'type', 'parentId', 'permission']);
        }
        if(data) {
            form.setFieldsValue({name: data.name, url: data.url, permission: data.permission, type: data.type})
        }
    })

    const formItemLayout = {
        labelCol: {span: 6},
        wrapperCol: {span: 14},
    }

    const submit = () => {
        form.submit();
    }

    const finish = values => {
        add(values);
    }

    return (
        <AntForm.Provider>
            <Modal
                title="菜单管理"
                visible={visible}
                onCancel={() => setVisible(false)}
                width="500px"
                footer={[
                    <Button key="back" onClick={() => setVisible(false)}>
                        关闭
                    </Button>,
                    <Button key="submit" type="primary" loading={loading} onClick={submit}>
                        提交
                    </Button>
                ]}
            >
                <AntForm
                    {...formItemLayout}
                    onFinish={finish}
                    form={form}
                >
                    <AntForm.Item
                        label="上级菜单"
                        name="parentId"
                    >
                        <TreeSelect
                            style={{width: '100%'}}
                            dropdownStyle={{maxHeight: 400, overflow: 'auto'}}
                            treeData={treeData}
                            placeholder="Please select"
                            treeDefaultExpandAll
                            defaultValue={2}
                        />
                    </AntForm.Item>
                    <AntForm.Item
                        label="名称"
                        name="name"
                    >
                        <Input/>
                    </AntForm.Item>
                    <AntForm.Item
                        label="访问地址"
                        name="url"
                    >
                        <Input/>
                    </AntForm.Item>
                    <AntForm.Item
                        label="权限标识"
                        name="permission"
                    >
                        <Input/>
                    </AntForm.Item>
                    <AntForm.Item
                        label="类型"
                        name="type"
                    >
                        <Radio.Group>
                            <Radio value={0}>菜单</Radio>
                            <Radio value={1}>按钮</Radio>
                        </Radio.Group>
                    </AntForm.Item>
                </AntForm>
            </Modal>
        </AntForm.Provider>
    )

}

function mapStateToProps(state) {
    return {
        visible: state.menuFormInfo.visible,
        treeData: state.menuInfo.treeData,
        loading: state.menuFormInfo.loading,
        data: state.menuFormInfo.data
    }
}

function mapDispatchToProps(dispatch) {
    return {
        setVisible: (visible) => dispatch(setVisibleMode_action(visible)),
        add: (model) => dispatch(add_action(model))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);