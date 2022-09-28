package com.Exercise.GroceryStore.Controllers;

import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminPanelController {


    @Autowired
    ItemService itemService;


    //Add Item to inventory
    @PostMapping("/addItem")
    public ResponseEntity<String> addQuestion( @Valid @RequestBody Item item) {




            //Confirm that price is valid
            if(item.getItemPrice() <= 0){
                return ResponseEntity.badRequest()
                        .body("Item price cannot be below or equal to zero!");
            }



            //Confirm that item description is valid and matches Apples or Vegetables
            if (!item.getItemDescription().toUpperCase().equals("APPLE")
                    && !item.getItemDescription().toUpperCase().equals("VEGETABLE"))
            {
                return ResponseEntity.badRequest()
                        .body("Item can only be [APPLE] or [VEGETABLE]!");
            }


            itemService.saveItem(item);
            return ResponseEntity.ok("Item created!");

    }

    @GetMapping("/getInventory")
    public ResponseEntity<List<Item>> getInventory( ) {
        return ResponseEntity.ok(itemService.getAll());
    }


}
