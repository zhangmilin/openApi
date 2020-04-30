package com.zhang.openApi.common.exception;

import com.zhang.openApi.api.enums.ResultCode;
import com.zhang.openApi.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @description 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);


    @ExceptionHandler(BindException.class)
    public ResultVO methodArgumentNotValid(BindException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < allErrors.size(); i++) {
            ObjectError error = allErrors.get(i);
            errorMessage.append(error.getDefaultMessage());
            if (i != allErrors.size() - 1) {
                errorMessage.append(",");
            }
        }
        return ResultVO.error(errorMessage.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ResultVO.error(objectError.getDefaultMessage());
    }

    @ExceptionHandler(ClientExistException.class)
    public ResultVO ClientExistException(ClientExistException e) {
        return ResultVO.error(e.getMessage());
    }

    @ExceptionHandler(ClientLockedException.class)
    public ResultVO ClientLockedException(ClientLockedException e) {
        return ResultVO.error(e.getMessage());
    }

    /**
     * 最后拦截所有的异常
     * @param e
     * @return
     */
    @ExceptionHandler()
    public ResultVO ExceptionHandler(Exception e) {
        String errInfo = "程序报错，进入全局异常处理";
        LOGGER.error("{}  e={} ",errInfo ,e+"");
        // 从异常对象中拿到ObjectError对象
        String msg = e.getMessage() == null ? "系统出现异常" : e.getMessage();
        // 然后提取错误提示信息进行返回
        if (e instanceof AccessDeniedException) {
            return ResultVO.error(ResultCode.UNAUTHORIZED_ERROR);
        }
        return ResultVO.error(msg);
    }


}