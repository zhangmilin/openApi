package com.zhang.openApi.common.vo;

import com.zhang.openApi.api.constants.ApiConstant;
import com.zhang.openApi.api.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultVO<T> {

    @ApiModelProperty(value = "状态", notes = "默认是true")
    private boolean success = true;

    @ApiModelProperty(value = "状态码", notes = "默认1000是成功")
    private int code = 1000;
    @ApiModelProperty(value = "响应信息", notes = "来说明响应情况")
    private String msg;
    @ApiModelProperty(value = "响应的具体数据")
    private T data;

    public ResultVO() {
    }

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }


    public ResultVO(int code, String msg, boolean status, T data) {
        this.code = code;
        this.msg = msg;
        this.success = status;
        this.data = data;
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.success = resultCode.isSuccess();
    }

    public static ResultVO success() {
        return success("操作成功");
    }

    public static ResultVO success(String msg) {
        return success(msg, null);
    }

    public static ResultVO successData(Object data) {
        return success("操作成功", data);
    }

    public static ResultVO success (String msg, int code) {
        return new ResultVO(code, msg, ApiConstant.STATUS_SUCCESS, null);
    }

    public static ResultVO success(String msg, Object data) {
        return new ResultVO(ApiConstant.CODE_SUCCESS, msg, ApiConstant.STATUS_SUCCESS, data);
    }

    public static ResultVO error(String msg) {
        ResultVO resultBean = new ResultVO();
        resultBean.setMsg(msg);
        resultBean.setSuccess(ApiConstant.STATUS_ERROR);
        resultBean.setCode(ApiConstant.CODE_ERR);
        return resultBean;
    }

    public static ResultVO error(ResultCode resultCode) {
        return new ResultVO(resultCode);
    }
}