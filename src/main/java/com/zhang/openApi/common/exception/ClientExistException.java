package com.zhang.openApi.common.exception;

/**
 * 客户端不存在异常
 * @author zhangfeng
 * @date 2020/4/29 5:09 下午
 */
public class ClientExistException extends RuntimeException {

    public ClientExistException(String message) {
        super(message);
    }
}
