package com.lighter.granate.dto;

public class CreateDishDTO {
    private String title;
    private float price;
    private String img;


    public String getTitle() {
        return this.title;
    }

    public float getPrice() {
        return this.price;
    }

    public String getImg() {
        return this.img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
