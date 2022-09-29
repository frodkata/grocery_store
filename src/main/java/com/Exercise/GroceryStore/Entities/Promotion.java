package com.Exercise.GroceryStore.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String promotedCategory;

    @Column(name = "type")
    private String promotionType;

    @ElementCollection
    @CollectionTable(name = "promotedItems")
    private List<String> promotedItemNames;

    public Promotion() {
    }

    public Promotion(String promotedCategory, String promotionType) {
        this.promotedCategory = promotedCategory;
        this.promotionType = promotionType;
    }

    public Promotion(String promotedCategory, String promotionType, List<String> promotedItemNames) {
        this.promotedCategory = promotedCategory;
        this.promotionType = promotionType;
        this.promotedItemNames = promotedItemNames;
    }

    public String getPromotedCategory() {
        return promotedCategory;
    }

    public void setPromotedCategory(String promotedCategory) {
        this.promotedCategory = promotedCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public List<String> getPromotedItemNames() {
        return promotedItemNames;
    }

    public void setPromotedItemNames(List<String> promotedItemNames) {
        this.promotedItemNames = promotedItemNames;
    }
}
