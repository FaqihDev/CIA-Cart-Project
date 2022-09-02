package com.demo.CartProject.Service.impl;


import com.demo.CartProject.Common.BaseResponse;
import com.demo.CartProject.Common.ResponseCode;
import com.demo.CartProject.Entity.Cart;
import com.demo.CartProject.Entity.Checkout;
import com.demo.CartProject.Entity.Product;
import com.demo.CartProject.Entity.User;
import com.demo.CartProject.Repository.CartRepository;
import com.demo.CartProject.Repository.CheckoutRepository;
import com.demo.CartProject.Repository.ProductRepository;
import com.demo.CartProject.Repository.UserRepository;
import com.demo.CartProject.Service.CartService;
import com.demo.CartProject.dto.CartRequest;
import com.demo.CartProject.dto.CartTotalPriceResponseDto;
import com.demo.CartProject.exception.DataNotFoundException;
import com.demo.CartProject.exception.OutOfStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class CartServiceImpl implements CartService {

    //Define Cart Repository
    @Autowired
    private CartRepository cartRepository;

   //Define User & product Repository untuk ambil user dan product
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;


    @Override
    @Bean
    public Cart cart() {
        Cart cart = new Cart();
        return cart;
    }

    @Override
    public void addSelectProductToCart(CartRequest param) {

        Cart cart = new Cart();  //Buat object Cart


        User user = userRepository.findById(param.getUserId()).orElse(null); //Cari User berdasarkan id
        cart.setUserId(user); //lalu set cart kedalam user


        Product product = productRepository.findById(param.getProductId()).orElse(null); //Cari Product berdasarkan id
        cart.setProductId(product); //lalu set cart kedalam user
        assert product != null;
        //Set attribute Cart request

        //Tentukan jumlah dari attribute yang ada di product
        cart.setAmount(product.getPrice());

        //Ambil harga total yang setelah semua di jumlahkan
        cart.setTotalPrice(product.getPrice() * param.getQuantity());
        cart.setIsCancel(0);
        cart.setCreatedBy("User");
        Date today = Date.from(Instant.now());
        cart.setCreatedDate(today);

        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllProductInCart(Long userId) {
        //Ambil user by id
        User user = userRepository.findById(userId).orElse(null);
        List<Cart> getAllByUserId = cartRepository.findAllByUserId(user);
        //buat variable list cart dan findByUserId
        System.out.println("All By User id" + getAllByUserId );
        return getAllByUserId;
    }

    @Override
    public CartTotalPriceResponseDto getFinalTotalPrice(Long userId) {

        List<Cart> allProductInCart = this.getAllProductInCart(userId); //Buat variable baru dan Ambil semua cart

        int totalPrice = 0; //buat variable kosong untuuk total
        for (Cart cart : allProductInCart){ //looping dan jumlahkan hasil harga dari masing2 product yang ada di cart
            totalPrice += cart.getTotalPrice();
        }

        CartTotalPriceResponseDto c = new CartTotalPriceResponseDto();  //Buat Objek CartResponse
        c.setUserId(userId); //Set user kedalam CartResponse
        c.setTotalPrice(totalPrice); //set total harga yang sudah di looping
        c.setTransactionId(UUID.randomUUID().toString()); //set transaction id

        //Buat object checkout
        Checkout checkout = new Checkout();


        User user = userRepository.findById(userId).orElse(null); //Ambil user by id
        checkout.setUserId(user);//set userId kedalam user
        checkout.setTotalPrice(totalPrice);//set total price
        checkout.setTransactionId(UUID.randomUUID().toString());//set transaction id

        checkoutRepository.save(checkout);//save kedalam repository

        return c; // return cart response


    }

    @Override
    public void deleteProductInCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> addProductToChart(CartRequest request){
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new DataNotFoundException(ResponseCode.FAILED.getCode(), "Data not found"));

            if(product.getQuantity() < 1){
                throw new OutOfStockException(ResponseCode.FAILED.getCode(), "Product is out of stock");
            }

            Cart cart = Cart.builder()
                    .productId(product)
                    .totalPrice(product.getPrice() * request.getQuantity())
                    .isCancel(0)
                    .build();

            cartRepository.save(cart);

            return ResponseEntity.ok().body(new BaseResponse<>(ResponseCode.SUCCESS.getCode(),"Add to chart success",null));
        } catch (Exception e){
            e.getMessage();
            return ResponseEntity.badRequest().body(new BaseResponse<>(ResponseCode.FAILED.getCode(),"Add to chart failed",null));
        }
    }
}
