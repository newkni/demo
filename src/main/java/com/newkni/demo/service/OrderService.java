package com.newkni.demo.service;

import com.newkni.demo.domain.Order;
import com.newkni.demo.domain.OrderSearchDto;
import com.newkni.demo.domain.Product;

import java.util.List;
import java.util.Optional;


public interface OrderService {
    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order createOrder(Order order, Product product);
    Order save(Order order);

    List<Order> findByMemberId(Long id);

    List<Order> searchOrders(OrderSearchDto dto, List<Product> products);
}
