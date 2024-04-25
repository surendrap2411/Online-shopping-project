package com.example.Online_shopping_project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_shopping_project.DTO.ProductDTO;
import com.example.Online_shopping_project.Entity.Product;
import com.example.Online_shopping_project.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());

            return productRepository.save(existingProduct);
        } else {
            
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    @Override
    public String deleteProduct(Long productId) {
        productRepository.deleteById(productId);
		return  null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

	@Override
	public Product convertToProduct(ProductDTO product) {
		
		return null;
	}
}
