package com.Exercise.GroceryStore.Services;
import static org.mockito.Mockito.when;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;


    @Test
    public void shouldGetCartList(){
        List<Cart> carts = new ArrayList<>();


        given(cartRepository.findAll()).willReturn(carts);


        List<Cart> expected = cartService.getAll();
        assertEquals(expected, carts);
    }


    @Test
    public void saveCartTest(){
        Cart cart = new Cart();
        cartService.saveCart(cart);
        verify(cartRepository, times(1)).save(cart);

    }

    @Test
    public void shouldGetSingleCart(){
        List<Cart> carts = new ArrayList<>();
        Cart expected = new Cart();
        carts.add(expected);


        given(cartRepository.findAll()).willReturn(carts);

        assertEquals(expected, cartService.getCart());
    }

    @Test
    public void shouldDeleteCart(){
        Cart cart = new Cart();
        cart.setId(1L);


        cartService.deleteCartById(1L);

        verify(cartRepository, times(1)).deleteById(1L);
    }

}