package com.demo.CartProject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException{

    private String responseCode;
    private String responseMessage;

    public DataNotFoundException(String responseCode) {
        this.responseCode = responseCode;
    }
}
