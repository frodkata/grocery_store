package com.Exercise.GroceryStore.Entities;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Validated
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @Column(name = "description")
    @NotNull
    @NotBlank(message = "Description is mandatory")
    private String itemDescription;

    @Column(name = "price")
    @NotNull
    private Double itemPrice;

    public Item() {
    }

    public Item(Long id, String itemDescription, Double itemPrice) {
        this.id = id;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
