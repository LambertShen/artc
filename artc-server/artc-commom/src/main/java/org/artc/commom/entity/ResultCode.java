package org.artc.commom.entity;

public enum ResultCode {

    SUCCESS(true, 10000, "操作成功!"),
    FAIL(false, 10001, "操作失败!"),
    UNAUTHENTICATED(false, 10002, "您还未登录！"),
    UNAUTHORISED(false, 10003, "权限不足！"),
    USER_NOT_EXIST(false, 20001, "用户不存在！"),
    USER_ACCOUNT_DISABLED(false, 20002, "账号已被禁止登录！"),
    USER_PASSWORD_ERROR(false, 20003, "密码错误！"),
    SERVER_ERROR(false, 99999, "抱歉, 系统繁忙, 请稍后重试!");

    //操作是否成功
    boolean success;
    //操作码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
