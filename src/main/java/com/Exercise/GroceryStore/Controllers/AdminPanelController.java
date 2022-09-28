package com.Exercise.GroceryStore.Controllers;

import com.Exercise.GroceryStore.Entities.*;
import com.Exercise.GroceryStore.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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



    //Add Item to inventory
    @PostMapping("/addItem")
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


    @GetMapping("/getInventory")
    public ResponseEntity<List<Item>> getInventory( ) {
        return ResponseEntity.ok(itemService.getAll());
    }


}
