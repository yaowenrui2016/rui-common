package indi.rui.common.web.exception;

import indi.rui.common.base.dto.Status;

public class BizException extends RuntimeException
        implements Status {
    private String code;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
    }

    public BizException(Status status, String message) {
        super(message);
        this.code = status.getCode();
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
