package com.spike.crowd.handler;

import com.spike.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基于注解的异常处理器类，将异常信息传给前端
 * 因为前后端只使用 json 进行数据通信，所以不需要考虑 html 请求时的异常处理
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultEntity<Object> resolveException(Exception e) {
        return ResultEntity.FailedWithoutData(e.getMessage());
    }
}
