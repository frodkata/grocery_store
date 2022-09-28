package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    //Since there are no user entities and only one user, there is only one cart instance
    @Override
    public Cart getCart() {

        Optional<Cart> optional = cartRepository.findById(1L);
        Cart cart = null;
        if (optional.isPresent()) {
            cart = optional.get();
        }

        return cart;
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }
}
