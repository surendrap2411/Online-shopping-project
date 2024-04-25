package com.example.Online_shopping_project.DTO;

import java.math.BigDecimal;

public class OrderItemDTO {

    private Long itemId;
    private Long orderId;
    private ProductDTO product;  
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderItemDTO() {
       
    }

    public OrderItemDTO(Long itemId, Long orderId, ProductDTO product, Integer quantity, BigDecimal unitPrice) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
