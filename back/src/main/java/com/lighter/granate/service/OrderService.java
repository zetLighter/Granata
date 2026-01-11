package com.lighter.granate.service;

import com.lighter.granate.entity.Dish;
import com.lighter.granate.entity.User;
import com.lighter.granate.repository.DishRepository;
import com.lighter.granate.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UsersService usersService;
    private final DishRepository dishRepository;

    public OrderService(OrderRepository orderRepository, UsersService usersService, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.usersService = usersService;
        this.dishRepository = dishRepository;
    }

    public void removeOrder(int dishId) {
        User user = usersService.getUserFromToken();
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        orderRepository.deleteByUserAndDish(user, dish);
    }
}
