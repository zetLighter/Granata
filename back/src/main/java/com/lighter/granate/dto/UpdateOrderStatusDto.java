package com.lighter.granate.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderStatusDto {
    public int id;
    public String status;

    public int getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }
}
