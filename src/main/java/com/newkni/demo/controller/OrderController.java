package com.newkni.demo.controller;

import com.newkni.demo.domain.Order;
import com.newkni.demo.domain.OrderSearchDto;
import com.newkni.demo.domain.Product;
import com.newkni.demo.service.OrderService;
import com.newkni.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Optional<Product> product = productService.getProductById(order.getProductId());

        return product.map(value -> {
            Order createdOrder = orderService.createOrder(order, value);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/searchOrder")
    public List<Order> searchOrder(@RequestBody OrderSearchDto dto) {
        return orderService.searchOrders(dto, productService.getProducts());
    }


}
