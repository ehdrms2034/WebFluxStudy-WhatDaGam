package com.example.webfluxstudy.Model;

import lombok.Data;
import lombok.Getter;

public enum ErrCode {
    DUPLICATED_DATA(1001,"duplicated data exists!"),
    NOT_EXIST_DATA(1002, "data is not exists!");

    @Getter
    private int code;
    @Getter
    private String message;

    ErrCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
