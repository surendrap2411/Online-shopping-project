package com.example.Online_shopping_project.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_shopping_project.DTO.OrderDTO;
import com.example.Online_shopping_project.Entity.Order;
import com.example.Online_shopping_project.Entity.OrderItem;
import com.example.Online_shopping_project.Entity.Product;
import com.example.Online_shopping_project.Repository.OrderItemRepository;
import com.example.Online_shopping_project.Repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        Order order = convertToOrder(orderDTO);
        order = orderRepository.save(order);

        final Order finalOrder = order;

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(itemDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(finalOrder);

                    if (itemDTO.getProduct() != null) {
                        Product product = productService.convertToProduct(itemDTO.getProduct());
                        if (product != null && product.getProductId() != null) {
                            orderItem.setProduct(product);
                        } else {
                            throw new IllegalArgumentException("Product not found or invalid");
                        }
                    }else {
                        throw new IllegalArgumentException("Product is required for each order item");
                    }

                    orderItem.setQuantity(itemDTO.getQuantity());
                    orderItem.setUnitPrice(itemDTO.getUnitPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());

        // Associate order items with the saved order
        order.setOrderItems(orderItems);

        // Save the order again to cascade the changes to order items
        orderRepository.save(order);

        OrderDTO resultOrderDTO = new OrderDTO();
        resultOrderDTO.setOrderId(order.getOrderId());
        resultOrderDTO.setCustomerMobileNumber(order.getCustomerMobileNumber());
        resultOrderDTO.setOrderDate(order.getOrderDate());
        resultOrderDTO.setStatus(order.getStatus());
        resultOrderDTO.setTotalAmount(order.getTotalAmount());
        resultOrderDTO.setShippingAddress(order.getShippingAddress());
        return resultOrderDTO;
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            OrderDTO orderDTO = convertToOrderDTO(order);
            return orderDTO;
        }
        return null;
    }

    private OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomerMobileNumber(order.getCustomerMobileNumber());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setShippingAddress(order.getShippingAddress());
        return orderDTO;
    }

    public Order convertToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerMobileNumber(orderDTO.getCustomerMobileNumber());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(orderDTO.getStatus());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setShippingAddress(orderDTO.getShippingAddress());
        return order;
    }

    @Override
    public Order convertToOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }

}
