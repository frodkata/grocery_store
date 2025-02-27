package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        Item item = null;
        if (optional.isPresent()) {
            item = optional.get();
        }

        return item;
    }

    @Override
    public List<Item> getByCategory(String type) {
        List<Item> itemList = new ArrayList<>();
        for (Item item:itemRepository.findAll()) {
            if(item.getType().toUpperCase().equals(type.toUpperCase())){
                itemList.add(item);
            }
        }

        return itemList;
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
