package com.newkni.demo.service.impl;

import com.newkni.demo.domain.Order;
import com.newkni.demo.domain.OrderSearchDto;
import com.newkni.demo.domain.Product;
import com.newkni.demo.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    @Override
    public Order createOrder(Order order, Product product) {
        synchronized (this) {
            if (product.getStock() > 0) {
                product.setStock(product.getStock() - 1);
                return save(order);
            }
        }
        return null;
    }

    @Override
    public Order save(Order order) {
        order.setId(counter.incrementAndGet());
        orders.add(order);
        return order;
    }

    @Override
    public List<Order> findByMemberId(Long id) {
        return orders.stream().filter(order -> order.getMemberId().equals(id)).toList();
    }


    @Override
    public List<Order> searchOrders(OrderSearchDto dto, List<Product> products) {
        Optional<Product> first = products.stream().filter(product -> product.getProductName().equalsIgnoreCase(dto.productName())).findFirst();
        return orders.stream().filter(order ->
                (first.isEmpty() || order.getProductId().equals(first.get().getId()))
                        && (dto.start() == null || order.getOrderDate().isAfter(dto.start()))
                        && (dto.end() == null || order.getOrderDate().isBefore(dto.end()))).skip((long) dto.page() * dto.size()).limit(dto.size()).toList();
    }


}
