package com.demo.CartProject.Service;

import com.demo.CartProject.Entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    void saveNewProduct(Product param);

    void updateProduct(Product param, Long id);

    void deleteProduct(Long id);

    Product getProductById(Long id);

}
