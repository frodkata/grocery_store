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
    public Promotion getPromotion() {
        if(promotionRepository.findAll().isEmpty()){
            return new Promotion();
        }
        //Since only one promotion exists at any time, return last saved promotion
        return promotionRepository.findAll().get(
                promotionRepository.findAll().size()-1
        );
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
    public double calculatePriceByPromotion(Cart cart,String promoType, String promoCategory) {
        double totalPrice = 0;

        //Get total price
        for (Item i: cart.getItems()) {
            totalPrice+=i.getItemPrice();
        }




        //customer buys 3 items but only pays for the value of 2 of them, the cheapest one is free
        if(promoType.equals("2for3") && cart.getItems().size() >= 2){
            //assume first one is lowest
            double lowest = cart.getItems().get(0).getItemPrice();

            //go trough first 3 elements
            for (int i = 0; i <= 2; i++) {
                //check for a lower price
               if(cart.getItems().get(i).getItemPrice() < lowest){
                   lowest = cart.getItems().get(i).getItemPrice();
               }
            }

            totalPrice -= lowest;
        }
        else if(promoType.equals(("buy1get1"))){
            //Helper variable to count the number of items of same type
            int countItem = 0;
            for (Item i:cart.getItems()) {
                //Whenever an item that matches promotion category is detected, count one
                if(i.getItemDescription().equals(promoCategory)){
                    countItem++;
                }
                //The moment a second item of the same category is detected, reduce 50%
                if(countItem == 2){
                   totalPrice -= i.getItemPrice()*0.50;
                }
            }

        }



        return totalPrice;
    }
}
