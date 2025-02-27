package com.Exercise.GroceryStore.Controllers;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.Item;
import com.Exercise.GroceryStore.DTO.ItemDto;
import com.Exercise.GroceryStore.Services.CartService;
import com.Exercise.GroceryStore.Services.ItemService;
import com.Exercise.GroceryStore.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserPanelController {

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @Autowired
    PromotionService promotionService;

    //Fetch all available products
    @GetMapping("/products")
    public ResponseEntity<List<Item>> getProducts(){
        return ResponseEntity.ok(itemService.getAll()) ;
    }

    //Fetch items based on category
    @GetMapping("/products/{category}")
    public ResponseEntity<List<Item>> getByCategory(@PathVariable(name = "category") String category) {
        return ResponseEntity.ok(itemService.getByCategory(category));
    }

    //Fetch products in cart
    @GetMapping("/cart")
    public ResponseEntity<List<Item>> getCartProducts(){
        return ResponseEntity.ok(cartService.getCart().getItems()) ;
    }


    //Since there is only one user and no user entities, there is only one cart
    //already initialised on startup
    @PostMapping("/cart")
    public ResponseEntity<String> addToCart(@RequestBody Long productId)
    {
           Cart cart = cartService.getCart();

            //Check if item exists for given ID
            if(itemService.getItemById(productId) == null){
                return ResponseEntity.badRequest()
                        .body("Item not Found!");
            }


            //Fetch item from repo and add cart
            Item itemToAdd = itemService.getItemById(productId);
            itemToAdd.setCart(cart);
            cartService.saveCart(cart);



            return ResponseEntity.ok("Item " + itemToAdd.getName() + " added to cart!");

    }

    //Total price with active promotion is calculated at checkout
    @GetMapping("/checkout")
    public ResponseEntity<String> checkout(){

        //Check if cart is empty before checkout
        if(cartService.getCart().getItems().size() <= 0){
            ResponseEntity.badRequest()
                    .body("Cart is empty!");
        }



        //Calculate total price based on cart, promo type and promo category
        double totalPrice = promotionService.calculatePriceByPromotion(cartService.getCart());


        //Remove bought items from repository
        for (Item i: cartService.getCart().getItems()) {
            itemService.deleteItemById(i.getId());
        }

        String prettyResult = String.format("%.2f", totalPrice);
        return  ResponseEntity.ok("Total price of products: " + prettyResult);
    }

}
