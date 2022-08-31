package com.demo.CartProject.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartTotalPriceResponseDto {
    public Long userId;
    public Integer totalPrice;
    public String transactionId;

}
