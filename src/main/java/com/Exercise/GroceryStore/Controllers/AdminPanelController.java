package com.Exercise.GroceryStore.Controllers;

import com.Exercise.GroceryStore.Entities.Fruits;
import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Entities.ItemDto;
import com.Exercise.GroceryStore.Entities.Vegetables;
import com.Exercise.GroceryStore.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


        //Compare item name and assign corresponding item type
        for (Fruits f : Fruits.values()) {
            if (f.name().equals(item.getItemName().toUpperCase())) {
                newItem.setName(f.name());
                newItem.setItemDescription("FRUIT");
                break;
            }
        }

        //Compare item name and assign corresponding item type
        for (Vegetables v : Vegetables.values()) {
            if (v.name().equals(item.getItemName().toUpperCase())) {
                newItem.setName(v.name());
                newItem.setItemDescription("VEGETABLE");
                break;
            }
        }

        //Assert that item falls in supported category
        if(newItem.getItemDescription() == null){
               return ResponseEntity.badRequest()
                        .body("Unsupported item type! Has to be either a fruit: " + Arrays.toString(Fruits.values())
                        + " or a vegetable: " + Arrays.toString(Vegetables.values()));
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
