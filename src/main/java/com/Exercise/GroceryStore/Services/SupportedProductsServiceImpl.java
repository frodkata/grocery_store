package com.Exercise.GroceryStore.Services;


import com.Exercise.GroceryStore.Entities.SupportedProducts;
import com.Exercise.GroceryStore.Repositories.SupportedProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupportedProductsServiceImpl implements SupportedProductsService {

    @Autowired
    SupportedProductsRepository supportedProductsRepository;

    @Override
    public List<SupportedProducts> getAll() {
        return supportedProductsRepository.findAll();
    }

    @Override
    public SupportedProducts saveProduct(SupportedProducts product) {
        return supportedProductsRepository.save(product);
    }

    @Override
    public SupportedProducts getProductById(Long id) {
        Optional<SupportedProducts> optional = supportedProductsRepository.findById(id);
        SupportedProducts products = null;
        if (optional.isPresent()) {
            products = optional.get();
        }

        return products;
    }

    @Override
    public void deleteItemById(Long id) {
        supportedProductsRepository.deleteById(id);
    }

    @Override
    public List<SupportedProducts> fetchByType(String type) {
        List<SupportedProducts> productList = new ArrayList<>();

        //Fetch only products that are of the same type as requested
        for (SupportedProducts product : supportedProductsRepository.findAll()) {
            if (product.getType().equals(type)) {
                productList.add(product);
            }
        }

        return productList;
    }






    @Override
    public SupportedProducts fetchProductByName(String name) {
        //Fetch only products that are of the same type as requested
        for (SupportedProducts product : supportedProductsRepository.findAll()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public Boolean isSupportedItem(String itemName) {
        for (SupportedProducts product : supportedProductsRepository.findAll()) {
            if (product.getName().equals(itemName)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public Boolean isSupportedCategory(String category) {
        for (SupportedProducts product : supportedProductsRepository.findAll()) {
            if (product.getType().equals(category)) {
                return true;
            }

        }
        return false;
    }

}