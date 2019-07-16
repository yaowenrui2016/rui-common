package indi.rui.common.web.exception.handler;

import indi.rui.common.base.dto.DefaultStatus;
import indi.rui.common.base.dto.Response;
import indi.rui.common.web.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BizExceptionHandler {
    @ExceptionHandler
    public Response handleBizException(BizException be) {
        return Response.res(be.getCode(), be.getMessage());
    }

    @ExceptionHandler
    public Response handleRuntimeException(RuntimeException re) {
        return Response.res(DefaultStatus.EXCEPTION, re.getMessage());
    }

    @ExceptionHandler
    public Response handleException(Exception e) {
        return Response.error();
    }
}
