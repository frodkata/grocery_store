package com.Exercise.GroceryStore.DTO;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class ProductDto {

    @NotBlank
    public String itemName;

    @NotBlank
    public String itemType;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
