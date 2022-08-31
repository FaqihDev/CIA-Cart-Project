package com.demo.CartProject.Service.impl;


import com.demo.CartProject.Entity.Product;
import com.demo.CartProject.Repository.ProductRepository;
import com.demo.CartProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAll() {
       return productRepository.findAll();
    }

    @Override
    public void saveNewProduct(Product product) {
       product.setCreatedBy("Admin");
       product.setIsDeleted(0);
       Date today = Date.from(Instant.now());
       product.setCreatedDate(today);
       product.setDescription("To be updated");
       productRepository.save(product);
    }

    @Override
    public void updateProduct(Product param,Long id) {
         Product product = productRepository.findById(id).get();//Ambil terlebih dahulu user yang ingin dirubah berdasarkan Id
         product.setName(param.getName());
         product.setBrand(param.getBrand());
         product.setCode(param.getCode());
         product.setQuantity(param.getQuantity());
         product.setPrice(param.getPrice());
         product.setImage(param.getImage());
         Date today = Date.from(Instant.now());
         product.setUpdatedBy("Admin");
         product.setUpdatedDate(today);
         productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
