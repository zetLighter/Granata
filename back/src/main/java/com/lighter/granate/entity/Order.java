package com.lighter.granate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;
    @JsonIgnore
    @JoinColumn(name = "dish_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Dish dish;
    @Column(name = "dish_id", insertable = false, updatable = false)
    private int dishId;
    private int quantity;
    private String status;
    private String place;
    private String time;

    public Order(int id, User user, int userId, Dish dish, int dishId, int quantity, String status, String place, String time) {
        this.id = id;
        this.user = user;
        this.userId = userId;
        this.dish = dish;
        this.dishId = dishId;
        this.quantity = quantity;
        this.status = status;
        this.place = place;
        this.time = time;
    }

    public Order() {
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public int getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public int getUserId() {
        return this.userId;
    }

    public Dish getDish() {
        return this.dish;
    }

    public int getDishId() {
        return this.dishId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getStatus() {
        return this.status;
    }

    public String getPlace() {
        return this.place;
    }

    public String getTime() {
        return this.time;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class OrderBuilder {
        private int id;
        private User user;
        private int userId;
        private Dish dish;
        private int dishId;
        private int quantity;
        private String status;
        private String place;
        private String time;

        OrderBuilder() {
        }

        public OrderBuilder id(int id) {
            this.id = id;
            return this;
        }

        @JsonIgnore
        public OrderBuilder user(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        @JsonIgnore
        public OrderBuilder dish(Dish dish) {
            this.dish = dish;
            return this;
        }

        public OrderBuilder dishId(int dishId) {
            this.dishId = dishId;
            return this;
        }

        public OrderBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder place(String place) {
            this.place = place;
            return this;
        }

        public OrderBuilder time(String time) {
            this.time = time;
            return this;
        }

        public Order build() {
            return new Order(this.id, this.user, this.userId, this.dish, this.dishId, this.quantity, this.status, this.place, this.time);
        }

        public String toString() {
            return "Order.OrderBuilder(id=" + this.id + ", user=" + this.user + ", userId=" + this.userId + ", dish=" + this.dish + ", dishId=" + this.dishId + ", quantity=" + this.quantity + ", status=" + this.status + ", place=" + this.place + ", time=" + this.time + ")";
        }
    }
}
