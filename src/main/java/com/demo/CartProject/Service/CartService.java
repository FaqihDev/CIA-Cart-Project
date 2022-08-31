package com.demo.CartProject.Service;

import com.demo.CartProject.Entity.Cart;
import com.demo.CartProject.dto.CartRequest;
import com.demo.CartProject.dto.CartTotalPriceResponseDto;

import java.util.List;

public interface CartService {

    void addSelectProductToCart(CartRequest param);

    List<Cart> getAllProductInCart(Long userId);

    CartTotalPriceResponseDto getFinalTotalPrice(Long userId);

    void deleteProductInCart(Long id);

}
