package com.demo.CartProject.Controller.handler;

import com.demo.CartProject.Common.BaseResponse;
import com.demo.CartProject.exception.DataNotFoundException;
import com.demo.CartProject.exception.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse dataNotFoundHandle(DataNotFoundException e){
        return new BaseResponse(e.getResponseMessage(),Integer.valueOf(e.getResponseCode()));
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse outOfStockHandle(OutOfStockException e){
        return new BaseResponse(e.getResponseMessage(),Integer.valueOf(e.getResponseCode()));
    }
}
