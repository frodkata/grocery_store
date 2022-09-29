package com.Exercise.GroceryStore.Repositories;

import com.Exercise.GroceryStore.Entities.SupportedProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportedProductsRepository extends JpaRepository<SupportedProducts, Long> {
}
