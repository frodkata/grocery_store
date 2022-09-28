package com.Exercise.GroceryStore.Repositories;

import com.Exercise.GroceryStore.Entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
