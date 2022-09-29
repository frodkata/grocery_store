package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Entities.SupportedProducts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupportedProductsService {
    List<SupportedProducts> getAll();
    SupportedProducts saveProduct(SupportedProducts products);
    SupportedProducts getProductById(Long id);
    void deleteItemById(Long id);
    List<SupportedProducts> fetchByType(String type);

    SupportedProducts fetchProductByName(String name);
    Boolean isSupportedItem(String itemName);
    Boolean isSupportedCategory(String category);
}
