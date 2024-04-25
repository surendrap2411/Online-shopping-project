package com.example.Online_shopping_project.Service;

import com.example.Online_shopping_project.DTO.ProductDTO;
import com.example.Online_shopping_project.Entity.Product;

public class ProductConversionService {

    public static Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        
        return product;
    }
}
