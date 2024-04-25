package com.example.Online_shopping_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_shopping_project.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
	
	 List<CartItem> findAllByShoppingCart_CustomerMobileNumber(String customerMobileNumber);
}
