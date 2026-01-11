package com.lighter.granate.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDishDTO {
    private int id;
    private String title;
    private float price;

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public float getPrice() {
        return this.price;
    }
}
