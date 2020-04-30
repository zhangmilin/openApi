package com.zhang.openApi.api.enums;

import com.zhang.openApi.api.constants.ApiConstant;

/**
 * @author RudeCrab
 * @description 响应码枚举
 */
public enum ResultCode {

    SUCCESS(200, "操作成功", ApiConstant.STATUS_SUCCESS),
    FAILED(1001, "响应失败", ApiConstant.STATUS_ERROR),
    VALIDATE_FAILED(1002, "参数校验失败", ApiConstant.STATUS_ERROR),

    TOKEN_IS_INVALID_FAILED(2000, "token无效", ApiConstant.STATUS_ERROR),
    TOKEN_IS_VALID(2001, "token有效", ApiConstant.STATUS_SUCCESS),

    BAD_REQUEST_ERROR(400, "Bad request", ApiConstant.STATUS_ERROR),
    UNAUTHORIZED_ERROR(401, "UNAUTHORIZED", ApiConstant.STATUS_ERROR),
    FORBIDDEN_ERROR(403, "Forbidden", ApiConstant.STATUS_ERROR),
    NOT_FOUND_ERROR(404, "not found", ApiConstant.STATUS_ERROR),

    ERROR(500, "未知错误", ApiConstant.STATUS_ERROR);

    private int code;
    private String msg;
    private boolean success;

    ResultCode(int code, String msg, boolean status) {
        this.code = code;
        this.msg = msg;
        this.success = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}