package indi.rui.common.web.exception;

import indi.rui.common.base.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GExceptionHandler {
    @ExceptionHandler
    public Response handlRuntimeException(RuntimeException re) {
        return Response.res("500", re.getMessage());
    }

    @ExceptionHandler
    public Response handleException(Exception e) {
        return Response.error();
    }
}
