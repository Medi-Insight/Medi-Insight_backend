package com.msight.mediinsight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    public static Result success(Object data) {
        return new Result(200, "ok", data);
    }
    public static Result fail(Object data) {
        return new Result(500, "fail", data);
    }
}
