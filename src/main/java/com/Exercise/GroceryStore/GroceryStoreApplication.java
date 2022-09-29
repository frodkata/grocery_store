package com.Exercise.GroceryStore;

import com.Exercise.GroceryStore.Entities.Cart;
import com.Exercise.GroceryStore.Entities.SupportedProducts;
import com.Exercise.GroceryStore.Services.CartService;
import com.Exercise.GroceryStore.Services.SupportedProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

@SpringBootApplication
public class GroceryStoreApplication {

	@Autowired
	CartService cartService;

	@Autowired
	SupportedProductsService productsService;

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreApplication.class, args);
	}

	//Instantiate and save new cart on startup
	//Since there are no user entities and only one user, there is only one cart instance
	@EventListener(ApplicationReadyEvent.class)
	public void createUponStart() {
		Cart cart = new Cart();
		cartService.saveCart(cart);
		//Fruits
		productsService.saveProduct(new SupportedProducts("Fruit", "Apple"));
		productsService.saveProduct(new SupportedProducts("Fruit", "Orange"));
		productsService.saveProduct(new SupportedProducts("Fruit", "Mango"));
		productsService.saveProduct(new SupportedProducts("Fruit", "Papaya"));

		//Vegetable
		productsService.saveProduct(new SupportedProducts("Vegetable", "Cucumber"));
		productsService.saveProduct(new SupportedProducts("Vegetable", "Tomato"));
		productsService.saveProduct(new SupportedProducts("Vegetable", "Potato"));
		productsService.saveProduct(new SupportedProducts("Vegetable", "Onion"));



	}

}
