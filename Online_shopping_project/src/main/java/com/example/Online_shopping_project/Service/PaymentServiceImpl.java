package com.example.Online_shopping_project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_shopping_project.DTO.OrderDTO;
import com.example.Online_shopping_project.DTO.PaymentDTO;
import com.example.Online_shopping_project.Entity.Order;
import com.example.Online_shopping_project.Entity.Payment;
import com.example.Online_shopping_project.Entity.PaymentStatus;
import com.example.Online_shopping_project.Repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public Payment initiatePayment(PaymentDTO paymentDTO) {
        
        
        Order order = orderService.convertToOrder(orderService.getOrderById(paymentDTO.getOrderId()));

        
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentStatus(PaymentStatus.PENDING);

        return paymentRepository.save(payment);
    }

    @Override
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(orderId);
            Order order = orderService.convertToOrder(orderDTO);

            List<Payment> payments = paymentRepository.findByOrder(order);

            if (!payments.isEmpty()) {
                Payment payment = payments.get(0);
                PaymentDTO paymentDTO = convertToPaymentDTO(payment);
                return paymentDTO;
            } else {
                return null;
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            // Handle the exception or rethrow it as needed
            throw new RuntimeException("An error occurred while fetching payment by order ID: " + orderId, e);
        }
    }


    private PaymentDTO convertToPaymentDTO(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Payment> getPaymentHistory() {
        
        return paymentRepository.findAll();
    }
}
