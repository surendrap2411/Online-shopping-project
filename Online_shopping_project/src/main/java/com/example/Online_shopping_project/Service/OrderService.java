package com.example.Online_shopping_project.Service;

import java.util.List;

import com.example.Online_shopping_project.DTO.OrderDTO;
import com.example.Online_shopping_project.Entity.Order;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long orderId);
    
    

    List<OrderDTO> getOrdersByCustomerMobileNumber(String customerMobileNumber);

	Order convertToOrder(OrderDTO orderDTO);

	Order convertToOrder(Long orderId);

	Order createOrder(Order order);
}