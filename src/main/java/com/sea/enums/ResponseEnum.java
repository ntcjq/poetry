package com.sea.enums;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/24
 */
public enum ResponseEnum {

    SUCCESS(0, "success");

    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
