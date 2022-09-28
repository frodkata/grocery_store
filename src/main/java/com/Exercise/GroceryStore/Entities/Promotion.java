package com.Exercise.GroceryStore.Entities;

import javax.persistence.*;

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
}
