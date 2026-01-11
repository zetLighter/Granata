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

    public Order(int id, User user, Dish dish, int dishId, int quantity, String status, String place, String time) {
        this.id = id;
        this.user = user;
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = (Order) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$dish = this.getDish();
        final Object other$dish = other.getDish();
        if (this$dish == null ? other$dish != null : !this$dish.equals(other$dish)) return false;
        if (this.getDishId() != other.getDishId()) return false;
        if (this.getQuantity() != other.getQuantity()) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$place = this.getPlace();
        final Object other$place = other.getPlace();
        if (this$place == null ? other$place != null : !this$place.equals(other$place)) return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Order;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final Object $dish = this.getDish();
        result = result * PRIME + ($dish == null ? 43 : $dish.hashCode());
        result = result * PRIME + this.getDishId();
        result = result * PRIME + this.getQuantity();
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $place = this.getPlace();
        result = result * PRIME + ($place == null ? 43 : $place.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        return result;
    }

    public String toString() {
        return "Order(id=" + this.getId() + ", user=" + this.getUser() + ", dish=" + this.getDish() + ", dishId=" + this.getDishId() + ", quantity=" + this.getQuantity() + ", status=" + this.getStatus() + ", place=" + this.getPlace() + ", time=" + this.getTime() + ")";
    }

    public static class OrderBuilder {
        private int id;
        private User user;
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
            return new Order(this.id, this.user, this.dish, this.dishId, this.quantity, this.status, this.place, this.time);
        }

        public String toString() {
            return "Order.OrderBuilder(id=" + this.id + ", user=" + this.user + ", dish=" + this.dish + ", dishId=" + this.dishId + ", quantity=" + this.quantity + ", status=" + this.status + ", place=" + this.place + ", time=" + this.time + ")";
        }
    }
}
