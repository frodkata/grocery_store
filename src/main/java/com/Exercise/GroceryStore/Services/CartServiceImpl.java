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

    //Since only one cart exists at any given time, return last saved one
    @Override
    public Cart getCart() {
        if(cartRepository.findAll().isEmpty()){
            return new Cart();
        }

        return cartRepository.findAll().get(
                cartRepository.findAll().size()-1
        );
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }
}
