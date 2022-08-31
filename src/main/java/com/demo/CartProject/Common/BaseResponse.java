package com.demo.CartProject.Common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final  long serialVersionUID = -6233145663410668178L;

    private Integer code = 999;
    private String message;
    private T data;

    public BaseResponse(String message, Integer code, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public BaseResponse(String message, Integer code){
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public BaseResponse(String message) {
        this.message = message;
    }
}
