package com.Exercise.GroceryStore;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class GroceryStoreApplication {

	@Autowired
	CartService cartService;

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreApplication.class, args);
	}

	//Instantiate and save new cart on startup
	//Since there are no user entities and only one user, there is only one cart instance
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		Cart cart = new Cart();
		cartService.saveCart(cart);
	}

}
