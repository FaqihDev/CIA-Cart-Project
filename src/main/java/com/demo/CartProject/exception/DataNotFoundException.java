package com.demo.CartProject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataNotFoundException extends RuntimeException{

    private String responseCode;
    private String responseMessage;
}
