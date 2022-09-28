package com.Exercise.GroceryStore.Repositories;

import com.Exercise.GroceryStore.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
