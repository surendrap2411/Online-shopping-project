package com.example.Online_shopping_project.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Online_shopping_project.DTO.CartItemDTO;
import com.example.Online_shopping_project.DTO.ProductDTO;
import com.example.Online_shopping_project.Entity.CartItem;
import com.example.Online_shopping_project.Entity.Product;
import com.example.Online_shopping_project.Entity.ShoppingCart;
import com.example.Online_shopping_project.Repository.CartItemRepository;
import com.example.Online_shopping_project.Repository.ProductRepository;
import com.example.Online_shopping_project.Repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CartItemDTO> getShoppingCart(String customerMobileNumber) {
        //Optional<ShoppingCart> shoppingCartOptional2 = shoppingCartRepository.findByCustomerMobileNumber(customerMobileNumber);
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(1L);

        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            return shoppingCart.getCartItems().stream()
                    .map(this::mapToCartItemDTO)
                    .collect(Collectors.toList());
        } else {
            return List.of(); 
        }
    }

    @Override
    @Transactional
    public void addToCart(String customerMobileNumber, ProductDTO productDTO) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByCustomerMobileNumber(customerMobileNumber);

        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            Optional<Product> productOptional = productRepository.findById(productDTO.getProductId());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                CartItem cartItem = new CartItem(productDTO.getQuantity(), shoppingCart, product);
                shoppingCart.addCartItem(cartItem);
                
                
                product.setReservedQuantity(product.getReservedQuantity() + productDTO.getQuantity());
                
                
                productRepository.save(product);
                cartItemRepository.save(cartItem);
                shoppingCartRepository.save(shoppingCart);
            } else {
                
                throw new RuntimeException("Product not found with id: " + productDTO.getProductId());
            }
        } else {
            
            throw new RuntimeException("Shopping cart not found for customer mobile number: " + customerMobileNumber);
        }
    }


    @Override
    @Transactional
    public void updateCartItem(String customerMobileNumber, CartItemDTO cartItemDTO) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByCustomerMobileNumber(customerMobileNumber);

        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemDTO.getItemId());

            if (cartItemOptional.isPresent()) {
                CartItem existingCartItem = cartItemOptional.get();
                existingCartItem.setQuantity(cartItemDTO.getQuantity());
                cartItemRepository.save(existingCartItem);
            } else {
                
                throw new RuntimeException("Cart item not found with id: " + cartItemDTO.getItemId());
            }
        } else {
           
            throw new RuntimeException("Shopping cart not found for customer mobile number: " + customerMobileNumber);
        }
    }

    @Override
    @Transactional
    public void removeCartItem(String customerMobileNumber, Long productId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByCustomerMobileNumber(customerMobileNumber);

        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            Optional<CartItem> cartItemOptional = shoppingCart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getProduct().getProductId().equals(productId))
                    .findFirst();

            if (cartItemOptional.isPresent()) {
                CartItem cartItemToRemove = cartItemOptional.get();
                shoppingCart.removeCartItem(cartItemToRemove);
                cartItemRepository.delete(cartItemToRemove);
            } else {
                
                throw new RuntimeException("Cart item not found for product id: " + productId);
            }
        } else {
            
            throw new RuntimeException("Shopping cart not found for customer mobile number: " + customerMobileNumber);
        }
    }

    @Override
    @Transactional
    public void clearCart(String customerMobileNumber) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByCustomerMobileNumber(customerMobileNumber);

        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            shoppingCart.getCartItems().forEach(cartItem -> {
                Product product = cartItem.getProduct();
                product.setReservedQuantity(product.getReservedQuantity() - cartItem.getQuantity());
                productRepository.save(product);
            });
            shoppingCart.clearCart();
            shoppingCartRepository.save(shoppingCart);
        } else {
            
            throw new RuntimeException("Shopping cart not found for customer mobile number: " + customerMobileNumber);
        }
    }

   private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        return new CartItemDTO(cartItem.getProduct().getProductId(), cartItem.getQuantity());
    }

}

