package com.example.Online_shopping_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_shopping_project.DTO.PaymentDTO;
import com.example.Online_shopping_project.Entity.Payment;
import com.example.Online_shopping_project.Service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/checkout")
    public ResponseEntity<Payment> initiateCheckout(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.initiatePayment(paymentDTO);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long orderId) {
        try {
            PaymentDTO paymentDTO = paymentService.getPaymentByOrderId(orderId);
            if (paymentDTO != null) {
                return ResponseEntity.ok(paymentDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception for further investigation
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while processing your request.");
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<Payment>> getPaymentHistory() {
        List<Payment> paymentHistory = paymentService.getPaymentHistory();
        return ResponseEntity.ok(paymentHistory);
    }
}

