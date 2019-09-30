package indi.rui.common.base.dto;

public enum DefaultStatus implements Status, IEnum {
    SUCCESS             ("00000000", "操作成功"),
    FAILED              ("11111111", "操作失败"),
    NO_PERMISSION       ("88888888", "您没有访问权限"),
    EXCEPTION           ("99999999", "系统异常"),
    RECORD_NOT_EXISTS   ("01001001", "记录不存在");

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

    @Override
    public String getValue() {
        return this.code + ":" + this.message;
    }
}