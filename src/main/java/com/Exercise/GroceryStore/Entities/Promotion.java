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

    @Column(name = "type")
    private String promotionType;

    @ElementCollection
    @CollectionTable(name = "promotedItems")
    private List<String> promotedItemNames;

    public Promotion() {
    }

    public Promotion( String promotionType) {
        this.promotionType = promotionType;
    }

    public Promotion( String promotionType, List<String> promotedItemNames) {
        this.promotionType = promotionType;
        this.promotedItemNames = promotedItemNames;
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
