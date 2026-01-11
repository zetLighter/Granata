package com.lighter.granate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "dishes")
@Entity
public class Dish {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(unique = true, nullable = false)
    private String title;
    private float price;
    private String img;
    @JsonIgnore
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> order = new ArrayList<>();


    public Dish(int id, String title, float price, String img, List<Order> order) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.img = img;
        this.order = order;
    }

    public Dish() {
    }

    public static DishBuilder builder() {
        return new DishBuilder();
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public float getPrice() {
        return this.price;
    }

    public String getImg() {
        return this.img;
    }

    public List<Order> getOrders() {
        return this.order;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setOrders(List<Order> cartItems) {
        this.order = cartItems;
    }

    public String toString() {
        return "Dish(id=" + this.getId() + ", title=" + this.getTitle() + ", price=" + this.getPrice() + ", img=" + this.getImg() + ", cartItems=" + this.getOrders() + ")";
    }

    public static class DishBuilder {
        private int id;
        private String title;
        private float price;
        private String img;
        private List<Order> order;

        DishBuilder() {
        }

        public DishBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DishBuilder title(String title) {
            this.title = title;
            return this;
        }

        public DishBuilder price(float price) {
            this.price = price;
            return this;
        }

        public DishBuilder img(String img) {
            this.img = img;
            return this;
        }

        public DishBuilder orders(List<Order> order) {
            this.order = order;
            return this;
        }

        public Dish build() {
            return new Dish(this.id, this.title, this.price, this.img, this.order);
        }

        public String toString() {
            return "Dish.DishBuilder(id=" + this.id + ", title=" + this.title + ", price=" + this.price + ", img=" + this.img + ", cartItems=" + this.order + ")";
        }
    }
}
