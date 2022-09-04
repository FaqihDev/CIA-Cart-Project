package com.demo.CartProject.Controller;


import com.demo.CartProject.Common.BaseResponse;
import com.demo.CartProject.Common.CommonCode;
import com.demo.CartProject.Common.CommonMessage;
import com.demo.CartProject.Entity.Cart;
import com.demo.CartProject.Service.CartService;
import com.demo.CartProject.dto.CartRequest;
import com.demo.CartProject.dto.CartTotalPriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    public CartService cartService;

    //add selected product to cart

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CartRequest param){
        return cartService.addProductToChart(param);
    }

    @GetMapping(value = "{userId}")
    public BaseResponse<Cart> getAllproductInList(@PathVariable("userId") long userId){
        try{
            List<Cart> allProductsInCart = cartService.getAllProductInCart(userId);
            return new BaseResponse(CommonMessage.FOUND,CommonCode.SUCCESS,allProductsInCart);
        } catch (RuntimeException e){
            return new BaseResponse(CommonMessage.NOT_FOUND,CommonCode.NOT_FOUND);
        } catch (Exception e){
            return new BaseResponse(CommonMessage.NOT_FOUND,CommonCode.BAD_REQUEST);
        }
    }

    @PostMapping(value = "{userId}")
    public BaseResponse<Cart> getTotalPrice(@PathVariable("userId") long userId){
        try{
           CartTotalPriceResponseDto cartResponse = cartService.getFinalTotalPrice(userId);//Buat Objek cart response dan ambil total price di service
            return new BaseResponse(CommonMessage.FOUND,CommonCode.SUCCESS,cartResponse);
        } catch (RuntimeException e){
            return new BaseResponse<>(CommonMessage.NOT_FOUND,CommonCode.NOT_FOUND);
        } catch (Exception e){
            new BaseResponse<>(CommonMessage.NOT_FOUND,CommonCode.BAD_REQUEST);
        }
        return null;
    }

    @DeleteMapping(value = "{id}")
    public BaseResponse<Cart> deleteProductInCart(@PathVariable("id") long userId){
        cartService.deleteProductInCart(userId);
        return new BaseResponse(CommonMessage.DELETED,CommonCode.SUCCESS);
    }

}
