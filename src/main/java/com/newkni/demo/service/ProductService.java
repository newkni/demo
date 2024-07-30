package com.newkni.demo.service;

import com.newkni.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void init();
    List<Product> getProducts();

    Optional<Product> getProductById(Long id);
}
