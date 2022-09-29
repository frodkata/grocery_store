package com.Exercise.GroceryStore.Controllers;

import com.Exercise.GroceryStore.DTO.ItemDto;
import com.Exercise.GroceryStore.DTO.ProductDto;
import com.Exercise.GroceryStore.DTO.PromotionDto;
import com.Exercise.GroceryStore.Entities.*;
import com.Exercise.GroceryStore.Services.ItemService;
import com.Exercise.GroceryStore.Services.PromotionService;
import com.Exercise.GroceryStore.Services.SupportedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminPanelController {


    @Autowired
    ItemService itemService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    SupportedProductsService productsService;

    @PostMapping("/inventory/new")
    public ResponseEntity<String> createNewProduct(@Valid @RequestBody ProductDto product) {
        SupportedProducts newProduct = new SupportedProducts();

        //Check if item already exists in supported list
        if(productsService.isSupportedItem(product.getItemName())){
            return ResponseEntity.badRequest()
                    .body("Item is already supported!");
        }

        newProduct.setName(product.getItemName());
        newProduct.setType(product.getItemType());
        productsService.saveProduct(newProduct);
        return ResponseEntity.ok("Added " + newProduct.getName());
    }

    //Add Item to inventory
    @PostMapping("/inventory")
    public ResponseEntity<String> addItem(@Valid @RequestBody ItemDto item) {

        //Object to be added to the repository
        Item newItem = new Item();



        //Check if item is supported
        if(productsService.isSupportedItem(item.getItemName())){
            newItem.setName(item.getItemName());

            //Assign corresponding type to name ex.[Apple - Fruit]
            newItem.setType(productsService.fetchProductByName(item.itemName).getType());
        }else{
            System.out.println(productsService.getAll());
           return ResponseEntity.badRequest()
                    .body("Item is not supported! Currently supported items: " + productsService.getAll().toString() );
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

    //Create promotion on specific items
    @PostMapping("/promotions/items")
    public ResponseEntity<String> addPromotionByItems(@RequestBody PromotionDto promotionDto){

        //Check if item is supported
        for (String itemName: promotionDto.getProductNames()) {
            if(!productsService.isSupportedItem(itemName)){
                return ResponseEntity.badRequest()
                        .body("This item is not in the supported list!");
            }
        }

        Promotion newPromotion = new Promotion();
        List<String> promotedList = new ArrayList<>();
        promotedList.addAll(promotionDto.getProductNames());
        newPromotion.setPromotedItemNames(promotedList);
        newPromotion.setPromotionType(promotionDto.getPromotionType());
        newPromotion.setPromotedCategory("No category");

        promotionService.savePromotion(newPromotion);

        return ResponseEntity.ok("Promotion Created!");
    }

    //Create promotion on specific category
    @PostMapping("/promotions/category")
    public ResponseEntity<String> addPromotionByCategory(@RequestBody Promotion promotion){

        //Check if category is a supported one
        if(!productsService.isSupportedCategory(promotion.getPromotedCategory())){
            return ResponseEntity.badRequest()
                    .body("Unsupported category!");
        }

        //Check if the promotion type is supported
        if(!promotion.getPromotionType().equals("2for3") && !promotion.getPromotionType().equals("buy1get1")){
            return ResponseEntity.badRequest()
                    .body("Unsupported promotion type!");
        }

        //When promoting by category, only one promotion of given type and category can exist
        //Check if other promotions exist
        if(promotionService.getPromotionByType(promotion.getPromotionType()) != null){
            return ResponseEntity.badRequest()
                    .body("Promotion type " + promotion.getPromotionType() + " already exists!");
        }else if(promotionService.getPromotionByCategory(promotion.getPromotedCategory()) != null){
            return ResponseEntity.badRequest()
                    .body("Promotion category " + promotion.getPromotedCategory() + " already exists!");
        }
        Promotion newPromotion = new Promotion();
        newPromotion.setPromotedCategory(promotion.getPromotedCategory());
        newPromotion.setPromotionType(promotion.getPromotionType());

        promotionService.savePromotion(newPromotion);


        return ResponseEntity.ok("Promotion created!");
    }

    @GetMapping("/promotion")
    public ResponseEntity<List<Promotion>> getPromotion(){
        return ResponseEntity.ok(promotionService.getAll());
    }

    @DeleteMapping("/promotion")
    public  ResponseEntity<String> deletePromotion(@RequestBody String promotionType){
       if(promotionService.getPromotionByType(promotionType) == null){
           return ResponseEntity.badRequest()
                   .body("Promotion doesn't exist!");
       }

        promotionService.deletePromotionById(
               promotionService
                       .getPromotionByType(promotionType)
                       .getId());

       return ResponseEntity.ok("Deleted promotion " + promotionType);
    }

    //Fetch all items that are currently listed
    @GetMapping("/inventory")
    public ResponseEntity<List<Item>> getInventory( ) {
        return ResponseEntity.ok(itemService.getAll());
    }


}
