package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {
    Promotion getPromotion();
    Promotion savePromotion(Promotion promotion);
    Promotion getPromotionById(Long id);
    void deletePromotionById(Long id);
    void deleteAll();
    double calculatePriceByPromotion(Cart cart, String promoType, String promoCategory);
}
