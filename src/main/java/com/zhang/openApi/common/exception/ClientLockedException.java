package com.zhang.openApi.common.exception;

/**
 * 客户端状态异常
 * @author zhangfeng
 * @date 2020/4/29 5:09 下午
 */
public class ClientLockedException extends RuntimeException {

    public ClientLockedException(String message) {
        super(message);
    }
}
