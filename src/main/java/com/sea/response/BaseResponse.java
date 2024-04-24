package com.sea.response;

import com.sea.enums.ResponseEnum;
import lombok.Data;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/24
 */
@Data
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public BaseResponse(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data = data;
    }
}
