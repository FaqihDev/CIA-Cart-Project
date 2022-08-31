package com.demo.CartProject.Entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends ModelBase {

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "product_brand",nullable = false)
    private String brand;

    @Column(name = "product_quantity",nullable = false)
    private Long quantity;

    @Column(name = "product_code",nullable = false)
    private Long code;

    @Column(name = "product_price",nullable = false)
    private Long price;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_exp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expDate;

}
