package com.Exercise.GroceryStore.Services;

import com.Exercise.GroceryStore.Entities.SupportedProducts;
import com.Exercise.GroceryStore.Repositories.SupportedProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SupportedProductsServiceTest {


    @Mock
    private SupportedProductsRepository supportedProductsRepository;

    @InjectMocks
    private SupportedProductsServiceImpl supportedProductsService;

    @Test
    void shouldGetProductList() {
        List<SupportedProducts> actualProducts = new ArrayList<>();
        given(supportedProductsRepository.findAll()).willReturn(actualProducts);

        List<SupportedProducts> expectedProducts = supportedProductsService.getAll();
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void shouldSaveProduct() {
        SupportedProducts product = new SupportedProducts();
        supportedProductsService.saveProduct(product);
        verify(supportedProductsRepository, times(1)).save(product);
    }

    @Test
    void shouldGetProductById() {
        SupportedProducts product = new SupportedProducts();
        product.setId(1L);

        given(supportedProductsRepository.findById(1L)).willReturn(java.util.Optional.of(product));

        assertEquals(product, supportedProductsService.getProductById(1L));
    }

    @Test
    void shouldDeleteItemById() {
        SupportedProducts product = new SupportedProducts();
        product.setId(1L);


        supportedProductsService.deleteItemById(1L);

        verify(supportedProductsRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldFetchByProductType() {
        List<SupportedProducts> productsList = new ArrayList<>();

        SupportedProducts fruitType = new SupportedProducts("Fruit", "Apple");
        SupportedProducts vegetableType = new SupportedProducts("Vegetable", "Potato");

        productsList.add(fruitType);
        productsList.add(vegetableType);

        given(supportedProductsRepository.findAll()).willReturn(productsList);

        List<SupportedProducts> expectedProductList = new ArrayList<>();
        expectedProductList.add(fruitType);

        assertEquals(expectedProductList, supportedProductsService.fetchByType("Fruit"));
    }

    @Test
    void fetchProductByName() {
        List<SupportedProducts> productsList = new ArrayList<>();

        SupportedProducts fruitType = new SupportedProducts("Fruit", "Apple");
        SupportedProducts vegetableType = new SupportedProducts("Vegetable", "Potato");

        productsList.add(fruitType);
        productsList.add(vegetableType);

        given(supportedProductsRepository.findAll()).willReturn(productsList);



        assertEquals(fruitType, supportedProductsService.fetchProductByName("Apple"));
    }

    @Test
    void isSupportedItem() {
        List<SupportedProducts> productsList = new ArrayList<>();

        SupportedProducts fruitType = new SupportedProducts("Fruit", "Apple");
        SupportedProducts vegetableType = new SupportedProducts("Vegetable", "Potato");

        productsList.add(fruitType);
        productsList.add(vegetableType);

        given(supportedProductsRepository.findAll()).willReturn(productsList);

        assertEquals(true, supportedProductsService.isSupportedItem("Potato"));
    }

    @Test
    void isSupportedCategory() {
        List<SupportedProducts> productsList = new ArrayList<>();

        SupportedProducts fruitType = new SupportedProducts("Fruit", "Apple");
        SupportedProducts vegetableType = new SupportedProducts("Vegetable", "Potato");

        productsList.add(fruitType);
        productsList.add(vegetableType);

        given(supportedProductsRepository.findAll()).willReturn(productsList);

        assertEquals(true, supportedProductsService.isSupportedCategory("Fruit"));
    }
}