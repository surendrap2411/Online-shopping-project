package com.example.Online_shopping_project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Online_shopping_project.DTO.CartItemDTO;
import com.example.Online_shopping_project.DTO.ProductDTO;
import com.example.Online_shopping_project.Service.ShoppingCartService;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService; 

    @GetMapping("/{customerMobileNumber}")
    public ResponseEntity<?> getShoppingCart(@PathVariable(name = "customerMobileNumber") String customerMobileNumber) {
        try {
            return ResponseEntity.ok(shoppingCartService.getShoppingCart(customerMobileNumber));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving shopping cart");
        }
    }

    @PostMapping("/{customerMobileNumber}/add")
    public ResponseEntity<?> addToCart(@PathVariable(name = "customerMobileNumber") String customerMobileNumber, @RequestBody ProductDTO productDTO) {
        try {
            shoppingCartService.addToCart(customerMobileNumber, productDTO);
            return ResponseEntity.ok("Product added to the cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding product to the cart");
        }
    }

    @PutMapping("/{customerMobileNumber}/update")
    public ResponseEntity<?> updateCartItem(@PathVariable(name = "customerMobileNumber") String customerMobileNumber, @RequestBody CartItemDTO cartItemDTO) {
        try {
            shoppingCartService.updateCartItem(customerMobileNumber, cartItemDTO);
            return ResponseEntity.ok("Cart item updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating cart item");
        }
    }

    @DeleteMapping("/{customerMobileNumber}/remove/{productId}")
    public ResponseEntity<?> removeCartItem(@PathVariable(name = "customerMobileNumber") String customerMobileNumber, @PathVariable Long productId) {
        try {
            shoppingCartService.removeCartItem(customerMobileNumber, productId);
            return ResponseEntity.ok("Product removed from the cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error removing product from the cart");
        }
    }

    @DeleteMapping("/{customerMobileNumber}/clear")
    public ResponseEntity<?> clearCart(@PathVariable(name = "customerMobileNumber") String customerMobileNumber) {
        try {
            shoppingCartService.clearCart(customerMobileNumber);
            return ResponseEntity.ok("Shopping cart cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error clearing shopping cart");
        }
    }
}
