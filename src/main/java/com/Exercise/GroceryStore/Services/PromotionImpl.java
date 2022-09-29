package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Entities.Promotion;
import com.Exercise.GroceryStore.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionImpl implements PromotionService {

    @Autowired
    PromotionRepository promotionRepository;


    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }



    @Override
    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotionById(Long id) {
        Optional<Promotion> optional = promotionRepository.findById(id);
        Promotion promotion= null;
        if (optional.isPresent()) {
            promotion = optional.get();
        }

        return promotion;
    }

    @Override
    public void deletePromotionById(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        promotionRepository.deleteAllInBatch();
    }

    @Override
    public Promotion getPromotionByCategory(String categoryName) {
        for (Promotion p: promotionRepository.findAll()) {
            if(p.getPromotedCategory().toUpperCase().equals(categoryName.toUpperCase())){
                return p;
            }
        }

        return null;
    }

    @Override
    public Promotion getPromotionByType(String promotionType) {
        for (Promotion p: promotionRepository.findAll()) {
            if(p.getPromotionType().toUpperCase().equals(promotionType.toUpperCase())){
                return p;
            }
        }


        return null;
    }

    @Override
    public Boolean checkIfItemIsPromoted(String promotedItem, String promotionType) {
        for (Promotion p: promotionRepository.findAll()) {
            if(p.getPromotionType().equals(promotionType) && p.getPromotedItemNames().contains(promotedItem)){
                return true;
            }

        }

        return false;
    }

    @Override
    public double calculatePriceByPromotion(Cart cart) {

       // String promoType = getPromotion().getPromotionType();
       // String promoCategory = getPromotion().getPromotedCategory();
        double totalPrice = 0;

        //Get total price
        for (Item i: cart.getItems()) {
            totalPrice+=i.getItemPrice();
        }

        for (Promotion promotion: promotionRepository.findAll()) {
            if (promotion.getPromotionType() != null && promotion.getPromotionType().equals("2for3") && cart.getItems().size() >= 2) {
                
                //Assume that the first item in the cart is the cheapest
                double cheapestPrice = cart.getItems().get(0).getItemPrice();

                for (int i = 0; i <= 2; i++) {
                    //Check if item is currently in promoted list and check if it's price is lower
                    if(checkIfItemIsPromoted(cart.getItems().get(i).getName(), "2for3") && cart.getItems().get(i).getItemPrice() < cheapestPrice){
                        cheapestPrice = cart.getItems().get(i).getItemPrice();
                    }
                }


                totalPrice -= cheapestPrice;
            }
            if (promotion.getPromotionType() != null && promotion.getPromotionType().equals(("buy1get1"))) {
                int itemCount = 0;
                for (Item item: cart.getItems()) {
                    if(checkIfItemIsPromoted(item.getName(), "buy1get1")){
                        itemCount++;
                    }

                    if(itemCount == 2){
                        totalPrice -= item.getItemPrice()*0.50;
                    }

                }
            }
        }

        return totalPrice;
    }
}
