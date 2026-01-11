package com.lighter.granate.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class CreateOrderDTO {
    private int dishId;
    private int quantity;


    public int getDishId() {
        return this.dishId;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
