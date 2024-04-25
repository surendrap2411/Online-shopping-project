package com.example.Online_shopping_project.Service;

import java.util.List;

import com.example.Online_shopping_project.DTO.PaymentDTO;
import com.example.Online_shopping_project.Entity.Payment;

public interface PaymentService {
    Payment initiatePayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentByOrderId(Long orderId);
    List<Payment> getPaymentHistory();
}

