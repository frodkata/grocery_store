package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {
    List<Promotion> getAll();
    Promotion savePromotion(Promotion promotion);
    Promotion getPromotionById(Long id);
    void deletePromotionById(Long id);
    void deleteAll();
    Promotion getPromotionByCategory(String categoryName);
    Promotion getPromotionByType(String promotionType);
    Boolean checkIfItemIsPromoted(String promotedItem, String promotionType);
    double calculatePriceByPromotion(Cart cart);
}
