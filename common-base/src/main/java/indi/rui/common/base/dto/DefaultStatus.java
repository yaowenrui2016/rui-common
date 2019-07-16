package indi.rui.common.base.dto;

public enum DefaultStatus implements Status {
    SUCCESS     ("00000000", "操作成功"),
    EXCEPTION   ("99999999", "系统异常");

    private String code;
    private String message;

    DefaultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}