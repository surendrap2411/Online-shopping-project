package com.example.Online_shopping_project.Service;

import java.util.List;

import com.example.Online_shopping_project.DTO.CartItemDTO;
import com.example.Online_shopping_project.DTO.ProductDTO;

public interface ShoppingCartService {

    List<CartItemDTO> getShoppingCart(String customerMobileNumber);

    void addToCart(String customerMobileNumber, ProductDTO productDTO);

    void updateCartItem(String customerMobileNumber, CartItemDTO cartItemDTO);

    void removeCartItem(String customerMobileNumber, Long productId);

    void clearCart(String customerMobileNumber);

	
}
