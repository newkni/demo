package com.newkni.demo.controller;

import com.newkni.demo.domain.Product;
import com.newkni.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
