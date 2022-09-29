package com.Exercise.GroceryStore.Controllers;

import com.Exercise.GroceryStore.DTO.ItemDto;
import com.Exercise.GroceryStore.Entities.*;
import com.Exercise.GroceryStore.Services.ItemService;
import com.Exercise.GroceryStore.Services.PromotionService;
import com.Exercise.GroceryStore.Utility.SupportedProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminPanelController {


    @Autowired
    ItemService itemService;

    @Autowired
    PromotionService promotionService;


    //Add Item to inventory
    @PostMapping("/inventory")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody ItemDto item) {

        //Object to be added to the repository
        Item newItem = new Item();


        //Check if item name is in the supported product map
        if(SupportedProducts.productList.containsKey(item.itemName)){
            newItem.setName(item.getItemName());

            //Assign corresponding key(product category) as the product description
            newItem.setItemDescription(SupportedProducts.productList.get(item.getItemName()));
        }else{
            return ResponseEntity.badRequest()
                    .body("Unsupported item type! \n Supported items: "
                            + Arrays.toString(SupportedProducts.productList.keySet().toArray()));


        }

        //Confirm that price is valid
        if(item.getItemPrice() == null || item.getItemPrice() <= 0){
            return ResponseEntity.badRequest()
                    .body("Item price cannot be below or equal to zero!");
        }else{
            newItem.setItemPrice(item.getItemPrice());
        }



        //Add item
        itemService.saveItem(newItem);
            return ResponseEntity.ok("Item created!");
        }


    //Two-for-one promotion on a specific category
    @PostMapping("/promotion")
    public ResponseEntity<String> addPromotion(@RequestBody Promotion promotion){

        //Check if category is a supported one
        if(!SupportedProducts.productList.containsValue(promotion.getPromotedCategory())){
          return ResponseEntity.badRequest()
                    .body("Unsupported category!");
        }

        //Check if the promotion type is supported
        if(!promotion.getPromotionType().equals("2for3") && !promotion.getPromotionType().equals("buy1get1")){
            return ResponseEntity.badRequest()
                    .body("Unsupported promotion type!");
        }

        //Clear previous promotion and instantiate new one
        promotionService.deleteAll();
        Promotion newPromotion = new Promotion();
        newPromotion.setPromotedCategory(promotion.getPromotedCategory());
        newPromotion.setPromotionType(promotion.getPromotionType());

        promotionService.savePromotion(newPromotion);


        return ResponseEntity.ok("Promotion created!");
    }

    @GetMapping("/promotion")
    public ResponseEntity<Promotion> getPromotion(){
        return ResponseEntity.ok(promotionService.getPromotion());
    }

    //Fetch all items that are currently listed
    @GetMapping("/inventory")
    public ResponseEntity<List<Item>> getInventory( ) {
        return ResponseEntity.ok(itemService.getAll());
    }


}
