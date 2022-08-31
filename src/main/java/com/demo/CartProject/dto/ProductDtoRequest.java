package com.demo.CartProject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductDtoRequest {

    private String name;
    private String brand;
    private Integer quantity;
    private Double price;
    private String image;
    private Date expDate;

}
