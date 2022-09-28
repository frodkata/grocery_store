package com.Exercise.GroceryStore.Entities;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


public class SupportedProducts {


    public static Map<String, String> productList = new HashMap<String, String>() {{
        //Supported fruits
        put("Apple", "Fruit");
        put("Orange", "Fruit");
        put("Watermelon", "Fruit");
        put("Mango", "Fruit");
        put("Avocado", "Fruit");

        //Supported vegetables
        put("Onion", "Vegetable");
        put("Tomato", "Vegetable");
        put("Potato", "Vegetable");
        put("Broccoli", "Vegetable");
        put("Cucumber", "Vegetable");
    }};


}
