package com.demo.CartProject.Repository;

import com.demo.CartProject.Entity.Checkout;
import com.demo.CartProject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout,Long> {
}
