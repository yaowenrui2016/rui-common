package indi.rui.common.base.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * yaowr
 * 2019/5/20
 */
@Getter
@Setter
public final class  Response<T> {
    private String status;

    private String message;

    private T data;

    private Response(Status status) {
        this(status,null);
    }

    private Response(Status status, T data) {
        this.status = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Response ok() {
        return new Response(DefaultStatus.SUCCESS, null);
    }

    public static <T> Response ok(T data) {
        return new Response<T>(DefaultStatus.SUCCESS, data);
    }

    public static Response error() {
        return new Response(DefaultStatus.EXCEPTION);
    }

    public static Response res(Status status) {
        return new Response(status);
    }

    public static Response res(String status, String message) {
        return new Response(status, message);
    }

    enum DefaultStatus implements Status {
        SUCCESS     ("00000000", "操作成功"),
        EXCEPTION   ("99999999", "服务器异常");

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
}
