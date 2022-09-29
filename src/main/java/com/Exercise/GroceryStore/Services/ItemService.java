package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<Item> getAll();
    Item saveItem(Item item);
    Item getItemById(Long id);
    List<Item> getByCategory(String type);
    void deleteItemById(Long id);
}
