package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<Cart> getAll();
    Cart saveCart(Cart cart);
    Cart getCart();
    void deleteCartById(Long id);
}
