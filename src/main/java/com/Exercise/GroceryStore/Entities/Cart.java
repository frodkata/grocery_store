package com.Exercise.GroceryStore.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="cart")
    @JsonManagedReference
    private List<Item> items;

    public Cart(Long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public Cart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
