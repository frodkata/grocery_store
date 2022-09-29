package com.Exercise.GroceryStore.Entities;

import javax.persistence.*;

@Entity
@Table(name = "supportedProducts")
public class SupportedProducts {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;



    public SupportedProducts() {
    }

    public SupportedProducts(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public SupportedProducts(Long id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name + " (" + this.type + ")";
    }
}
