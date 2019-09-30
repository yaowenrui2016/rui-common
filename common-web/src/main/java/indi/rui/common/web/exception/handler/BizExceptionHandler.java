package indi.rui.common.web.exception.handler;

import indi.rui.common.web.exception.NoPermissionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import indi.rui.common.base.dto.Response;
import indi.rui.common.web.exception.BizException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class BizExceptionHandler {
    @ExceptionHandler
    public Response handleBizException(BizException e) {
        if (e instanceof NoPermissionException) {
            throw e;
        }
        log.error(e.getMessage(), e);
        return Response.res(e.getCode(), e.getMessage());
    }

    @ExceptionHandler
    public Response handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        if (e instanceof DataIntegrityViolationException) { // 数据操作失败，包括insert、update、delete等违反约束
            return Response.fail();
        }
        return Response.error();
    }

    @ExceptionHandler
    public Response handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.error();
    }
}
