package com.demo.CartProject.Repository;

import com.demo.CartProject.Entity.Cart;
import com.demo.CartProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserId(User user);
}
