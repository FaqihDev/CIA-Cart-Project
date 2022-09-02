package com.demo.CartProject.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name ="cart")
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Cart extends ModelBase {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "is_cancel")
    private Integer isCancel;
}
