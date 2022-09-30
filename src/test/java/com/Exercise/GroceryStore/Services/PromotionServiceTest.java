package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Entities.Promotion;
import com.Exercise.GroceryStore.Repositories.PromotionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class PromotionServiceTest {
    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionImpl promotionService;

    @Test
    void shouldGetPromotionList() {
        List<Promotion> actualPromotions = new ArrayList<>();
        given(promotionRepository.findAll()).willReturn(actualPromotions);

        List<Promotion> expectedPromotions = promotionService.getAll();
        assertEquals(expectedPromotions, actualPromotions);

    }

    @Test
    void shouldSavePromotion() {
        Promotion promotion = new Promotion();
        promotionService.savePromotion(promotion);
        verify(promotionRepository, times(1)).save(promotion);
    }

    @Test
    void shouldGetPromotionById() {
        Promotion promotion = new Promotion();
        promotion.setId(1L);

        given(promotionRepository.findById(1L)).willReturn(java.util.Optional.of(promotion));

        assertEquals(promotion, promotionService.getPromotionById(1L));
    }

    @Test
    void shouldDeletePromotionById() {
        Promotion promotion = new Promotion();
        promotion.setId(1L);


        promotionService.deletePromotionById(1L);

        verify(promotionRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldDeleteAllPromotions() {
        promotionService.deleteAll();
        verify(promotionRepository, times(1)).deleteAllInBatch();

    }

    @Test
    void shouldGetPromotionByType() {
        List<Promotion> promotionList = new ArrayList<>();

        Promotion actualPromotion = new Promotion();
        actualPromotion.setPromotionType("buy1get1");
        promotionList.add(actualPromotion);

        given(promotionRepository.findAll()).willReturn(promotionList);


        assertEquals(actualPromotion, promotionService.getPromotionByType("buy1get1"));
    }

    @Test
    void shouldCheckIfItemIsPromoted() {
        List<Promotion> promotionList = new ArrayList<>();
        Promotion actualPromotion = new Promotion();

        List<String> promotedItemNames = new ArrayList<>();
        promotedItemNames.add("Apple");

        actualPromotion.setPromotionType("buy1get1");
        actualPromotion.setPromotedItemNames(promotedItemNames);
        promotionList.add(actualPromotion);

        given(promotionRepository.findAll()).willReturn(promotionList);

        assertEquals(true, promotionService.checkIfItemIsPromoted("Apple", "buy1get1"));

    }


    @Test
    void shouldCalculatePriceByPromotion() {
        List<Promotion> promotionList = new ArrayList<>();
        List<String> promotedItemNames = new ArrayList<>();
        List<String> promotedItemNames2 = new ArrayList<>();

        Promotion active2for3 = new Promotion("2for3");
        promotedItemNames.add("Apple");
        promotedItemNames.add("Banana");
        promotedItemNames.add("Tomato");

        active2for3.setPromotedItemNames(promotedItemNames);

        Promotion activebuy1get1 = new Promotion("buy1get1");
        promotedItemNames2.add("Potato");

        activebuy1get1.setPromotedItemNames(promotedItemNames2);


        promotionList.add(active2for3);
        promotionList.add(activebuy1get1);

        given(promotionRepository.findAll()).willReturn(promotionList);

        Cart cart = new Cart();
        List<Item> items = new ArrayList<>();
        cart.setItems(items);

        Item apple = new Item("Apple", "Fruit", 0.5);
        Item banana = new Item("Banana", "Fruit", 0.4);
        Item tomato = new Item("Tomato", "Vegetable", 0.3);
        Item potato = new Item("Potato", "Vegetable", 0.26);

        cart.getItems().add(apple);
        cart.getItems().add(banana);
        cart.getItems().add(banana);
        cart.getItems().add(potato);
        cart.getItems().add(tomato);
        cart.getItems().add(banana);
        cart.getItems().add(potato);


        assertEquals("1.99", String.format("%.2f", promotionService.calculatePriceByPromotion(cart)));
    }
}