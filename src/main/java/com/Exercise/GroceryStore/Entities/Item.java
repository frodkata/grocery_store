package com.Exercise.GroceryStore.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "name")
    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;


    @Column(name = "description")
    @NotNull
    @NotBlank(message = "Description is mandatory")
    private String itemDescription;

    @Column(name = "price")
    @NotNull
    private Double itemPrice;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="cart_id")
    private Cart cart;

    public Item() {
    }

    public Item(Long id, @NotBlank(message = "Name is mandatory") String name, @NotBlank(message = "Description is mandatory") String itemDescription, Double itemPrice) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
