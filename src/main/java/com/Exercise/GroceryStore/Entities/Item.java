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


    @Column(name = "type")
    @NotNull
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "price")
    @NotNull
    private Double itemPrice;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="cart_id")
    private Cart cart;

    public Item() {
    }

    public Item(Long id, @NotBlank(message = "Name is mandatory") String name, @NotBlank(message = "Type is mandatory") String type, Double itemPrice, Cart cart) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.itemPrice = itemPrice;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
