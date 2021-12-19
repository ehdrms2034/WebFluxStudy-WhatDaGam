package com.example.webfluxstudy.Model;

import lombok.NonNull;

public class BusinessException extends RuntimeException{

    private ErrCode errCode;


    public BusinessException() {
        errCode = null;
    }

    public BusinessException(ErrCode errCode) {
        super(errCode.getMessage());
        this.errCode = errCode;
    }
}
