package com.example.Online_shopping_project.DTO;

import java.math.BigDecimal;

public class PaymentDTO {
    private Long orderId;
    private BigDecimal amount;

    
    public Long getOrderId1() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

	public BigDecimal getAmount1() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getOrderId() {
		// TODO Auto-generated method stub
		return null;
	}
}