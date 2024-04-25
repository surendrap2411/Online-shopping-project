package com.example.Online_shopping_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_shopping_project.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderOrderId(Long orderId);

}