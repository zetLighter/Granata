package com.lighter.granate.repository;

import com.lighter.granate.entity.Order;
import com.lighter.granate.entity.Dish;
import com.lighter.granate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByUserAndDish(User user, Dish dish);
    Optional<List<Order>> findByUser(User user);
    void deleteByUserAndDish(User user, Dish dish);
}
