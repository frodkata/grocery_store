package com.Exercise.GroceryStore.Repositories;

import com.Exercise.GroceryStore.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
