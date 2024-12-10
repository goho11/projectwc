package com.metacoding.projectwc._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    private Boolean success;
    private String message;
    private T body;

    public static <T> Resp<T> ok(T body) {
        return new Resp<>(true, "성공", body);
    }

    public static <T> Resp<T> fail(String message) {
        return new Resp<>(false, message, null);
    }
}