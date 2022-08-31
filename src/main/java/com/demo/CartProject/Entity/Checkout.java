package com.demo.CartProject.Entity;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checkout")
@Entity
public class Checkout extends ModelBase {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "transaction_id")
    private String transactionId;
}
