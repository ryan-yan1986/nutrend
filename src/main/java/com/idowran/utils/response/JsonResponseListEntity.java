package com.idowran.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponseListEntity<T> extends BaseJsonResponseEntity {

    private Collection<T> data;

    public JsonResponseListEntity() {
    }

    public JsonResponseListEntity(Collection<T> content) {
        super(0, null, null);
        this.data = content;
    }

    public JsonResponseListEntity(int code, Collection<T> content, String msg, String error) {
        super(code, msg, error);
        this.data = content;
    }

    public static <T> JsonResponseListEntity<T> ok(Collection<T> content) {
        return ok(0, content, null);
    }

    public static <T> JsonResponseListEntity<T> ok(int code, Collection<T> content, String msg) {
        return new JsonResponseListEntity<>(code, content, msg, null);
    }

    public static <T> JsonResponseListEntity<T> fail(int code) {
        return fail(code, null);
    }

    public static <T> JsonResponseListEntity<T> fail(int code, String msg) {
        return fail(code, msg, msg);
    }

    public static <T> JsonResponseListEntity<T> fail(int code, String msg, String error) {
        return new JsonResponseListEntity<>(code, null, msg, error);
    }

    public static <T> boolean isRequestSuccess(JsonResponseListEntity<T> response) {
        return Objects.nonNull(response) && 0 == response.getCode();
    }

    public static <T> boolean isRequestContentEmpty(JsonResponseListEntity<T> response) {
        return Objects.nonNull(response) && 0 == response.getCode() && (Objects.isNull(response.getData()) || response.getData().isEmpty());
    }


}