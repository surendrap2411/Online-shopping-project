package com.example.Online_shopping_project.Service;

import java.util.List;
import java.util.Optional;

import com.example.Online_shopping_project.DTO.ProductDTO;
import com.example.Online_shopping_project.Entity.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long productId);
    Product addProduct(Product product);
    Product updateProduct(Long productId, Product updatedProduct);
    String deleteProduct(Long productId);
    List<Product> getProductsByCategory(String category);
	Product convertToProduct(ProductDTO product);
}