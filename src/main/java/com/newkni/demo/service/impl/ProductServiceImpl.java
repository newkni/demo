package com.newkni.demo.service.impl;

import com.newkni.demo.domain.Product;
import com.newkni.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    private final Random random = new Random();

    @Override
    public void init() {
        products.add(Product.builder().id(counter.incrementAndGet()).productName("Iphone").stock(randomStock(10)).build());
        products.add(Product.builder().id(counter.incrementAndGet()).productName("AMD").stock(randomStock(9)).build());
        products.add(Product.builder().id(counter.incrementAndGet()).productName("Tesla").stock(randomStock(8)).build());
        products.add(Product.builder().id(counter.incrementAndGet()).productName("Computer").stock(randomStock(7)).build());
        products.add(Product.builder().id(counter.incrementAndGet()).productName("Book").stock(randomStock(6)).build());
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();

    }

    private int randomStock(int num) {
        return random.nextInt(num) + 1;
    }
}
