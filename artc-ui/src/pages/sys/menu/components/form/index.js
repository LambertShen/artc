import React, {useState} from 'react';
import axios from 'axios';
import {Modal, Form as AntForm, Input, Radio, Button, TreeSelect} from "antd";
import './index.less';

const Form = (props) => {

    const {title, visible, treeSelect, handleCloseForm, handlerSubmitForm} = props;

    const [confirmLoading, setConfirmLoading] = useState(false);

    const [form] = AntForm.useForm();

    const formItemLayout = {
        labelCol: {span: 6},
        wrapperCol: {span: 14},
    }

    const submit = () => {
        setConfirmLoading(true)
        form.submit();
    }

    const onFinish = values => {
        axios.post(
            "/core/menu",
            {
                name: values.name,
                url: values.url,
                type: values.type,
                parentId: values.parentId ? values.parentId : "0",
                permission: values.permission
            }
        ).then((response) => {
            handlerSubmitForm();
            setConfirmLoading(false);
            form.resetFields(['name', 'url', 'type', 'parentId', 'permission']);
        })
    }

    return (
        <AntForm.Provider>
            <Modal
                title={title}
                visible={visible}
                onCancel={handleCloseForm}
                width="500px"
                footer={[
                    <Button key="submit" type="primary" loading={confirmLoading} onClick={submit}>
                        提交
                    </Button>
                ]}
            >
                <AntForm
                    {...formItemLayout}
                    onFinish={onFinish}
                    form={form}
                >
                    <AntForm.Item
                        label="上级菜单"
                        name="parentId"
                    >
                        <TreeSelect
                            style={{ width: '100%' }}
                            dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
                            treeData={treeSelect}
                            placeholder="Please select"
                            treeDefaultExpandAll
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
    );
}

export default Form;
