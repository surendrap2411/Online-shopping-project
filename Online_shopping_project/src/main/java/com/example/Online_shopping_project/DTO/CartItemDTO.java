package com.example.Online_shopping_project.DTO;

public class CartItemDTO {
	private Long productId;
    private Integer quantity;
    private Long itemId;
    
    public CartItemDTO() {
        
    }

    public CartItemDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
   
    @Override
    public String toString() {
        return "CartItemDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +            
                '}';
    }

	public String getItemId() {
	
		// TODO Auto-generated method stub
		return null;
	}

 
}

