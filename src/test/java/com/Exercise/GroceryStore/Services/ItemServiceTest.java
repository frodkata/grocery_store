package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Repositories.CartRepository;
import com.Exercise.GroceryStore.Repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;


    @Test
    void shouldGetItemsList() {
        List<Item> items = new ArrayList<>();


        given(itemRepository.findAll()).willReturn(items);

        List<Item> expected = itemService.getAll();
        assertEquals(expected, items);
    }

    @Test
    void shouldSaveItem() {
        Item item = new Item();
        itemService.saveItem(item);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void shouldGetItemById() {

        Item item = new Item();
        item.setId(1L);

        given(itemRepository.findById(1L)).willReturn(java.util.Optional.of(item));

        assertEquals(item, itemService.getItemById(1L));
    }

    @Test
    void shouldReturnByCategory() {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setType("Fruit");

        items.add(item);
        given(itemRepository.findAll()).willReturn(items);

        List<Item> itemsByCategory = new ArrayList<>();
        itemsByCategory.add(item);

        assertEquals(itemsByCategory, itemService.getByCategory("Fruit"));
    }

    @Test
    void shouldDeleteItemById() {
        Item item = new Item();
        item.setId(1L);



        itemService.deleteItemById(1L);

        verify(itemRepository, times(1)).deleteById(1L);
    }
}