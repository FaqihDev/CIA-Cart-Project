package com.demo.CartProject.dto;

import lombok.Data;

@Data
public class CartRequest {

    public Long userId;
    public Long productId;
    public Integer quantity;


}
