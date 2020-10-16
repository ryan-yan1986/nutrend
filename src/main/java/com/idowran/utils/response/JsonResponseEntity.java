package com.idowran.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponseEntity<T> extends BaseJsonResponseEntity {

    private T data;

    public JsonResponseEntity() {
    }

    public JsonResponseEntity(T data) {
        super(0, "", "");
        this.data = data;
    }

    public JsonResponseEntity(int code, T data, String msg, String error) {
        super(code, msg, error);
        this.data = data;
    }

    public static <T> JsonResponseEntity<T> ok() {
        return ok(0, null, null);
    }

    public static <T> JsonResponseEntity<T> ok(T data) {
        return ok(0, data, null);
    }

    public static <T> JsonResponseEntity<T> ok(int code, T data, String msg) {
        return new JsonResponseEntity<>(code, data, msg, null);
    }

    public static <T> JsonResponseEntity<T> fail(int code) {
        return fail(code, null);
    }

    public static <T> JsonResponseEntity<T> fail(int code, String msg) {
        return fail(code, msg, msg);
    }

    public static <T> JsonResponseEntity<T> fail(int code, String msg, String error) {
        return new JsonResponseEntity<>(code, null, msg, error);
    }

    public static <T> boolean isRequestSuccess(JsonResponseEntity<T> response) {
        return null != response && 0 == response.getCode() && null != response.getData();
    }
}
