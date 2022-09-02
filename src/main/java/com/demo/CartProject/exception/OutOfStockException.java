package com.demo.CartProject.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutOfStockException extends RuntimeException{

    @JsonProperty(value = "response_code")
    private String responseCode;
    @JsonProperty(value = "response_message")
    private String responseMessage;
}
