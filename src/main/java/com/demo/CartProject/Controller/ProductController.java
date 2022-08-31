package com.demo.CartProject.Controller;


import com.demo.CartProject.Common.BaseResponse;
import com.demo.CartProject.Common.CommonCode;
import com.demo.CartProject.Common.CommonMessage;
import com.demo.CartProject.Entity.Product;
import com.demo.CartProject.Service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping(value = "/getAll")
    public BaseResponse<Product> getProducts() {
        List<Product> products = productService.getAll();
        return new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, products);
    }

    @GetMapping(value = "/getById/{id}")
    public BaseResponse<Product> getProductById(@PathVariable("id") Long id) {
        try {
            Product products = productService.getProductById(id);
            new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, products);
        } catch (RuntimeException e) {
            new BaseResponse(CommonMessage.NOT_DELETED, CommonCode.NOT_FOUND);
        } catch (Exception e) {
            new BaseResponse(CommonMessage.NOT_DELETED, CommonCode.FORBIDDEN);
        }
        return null;
    }

    @PostMapping(value = "/add")
    public BaseResponse<Product> addProduct(@RequestBody Product param) {
        try {
            if (Objects.nonNull(param)) {
                productService.saveNewProduct(param);
                return new BaseResponse<>(CommonMessage.SAVED, CommonCode.SUCCESS, param);
            } else {
                return null;
            }
        } catch (Exception e) {
            return new BaseResponse<>(CommonMessage.NOT_SAVED, CommonCode.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{id}")
    public BaseResponse<Product> updateProduct(@RequestBody Product param, @PathVariable("id") Long id){
         try{
            productService.updateProduct(param,id);
            return new BaseResponse(CommonMessage.UPDATED,CommonCode.SUCCESS);
        } catch (RuntimeException e){
             return new BaseResponse(CommonMessage.NOT_UPDATED,CommonCode.NOT_FOUND);
         } catch (Exception e){
             return new BaseResponse<>(CommonMessage.NOT_UPDATED,CommonCode.BAD_REQUEST);
         }
    }

    @DeleteMapping(value = "/delete/{id}")
    public BaseResponse<Product> deleteProduct(@PathVariable("id") Long id){

            Product product = productService.getProductById(id);
            try{
                productService.deleteProduct(id);
                return new BaseResponse(CommonMessage.DELETED,CommonCode.SUCCESS);
            } catch (RuntimeException e){
                return new BaseResponse(CommonMessage.NOT_DELETED,CommonCode.NOT_FOUND);
            } catch (Exception e){
                return new BaseResponse(CommonMessage.NOT_DELETED,CommonCode.BAD_REQUEST);
            }
      }
}