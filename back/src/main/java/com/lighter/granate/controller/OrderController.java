package com.lighter.granate.controller;

import com.lighter.granate.dto.CreateOrderDTO;
import com.lighter.granate.entity.Order;
import com.lighter.granate.entity.Dish;
import com.lighter.granate.entity.User;
import com.lighter.granate.repository.OrderRepository;
import com.lighter.granate.repository.DishRepository;
import com.lighter.granate.repository.UserRepository;
import com.lighter.granate.service.OrderService;
import com.lighter.granate.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final UsersService usersService;
    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository, DishRepository dishRepository, UsersService usersService, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.usersService = usersService;
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody CreateOrderDTO dto) {
        User user = usersService.getUserFromToken();

        Dish dish = dishRepository.findById(dto.getDishId()).orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        Optional<Order> existingOrder = orderRepository.findByUserAndDish(user, dish);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setQuantity(order.getQuantity() + dto.getQuantity());
            orderRepository.save(order);
        } else {
            Order order = Order.builder()
                    .user(user)
                    .dish(dish)
                    .dishId(dish.getId())
                    .quantity(dto.getQuantity())
                    .status("В обработке")
                    .place("г. Ростов-на-Дону, ул. Мечникова, 79")
                    .time(DateTimeFormatter.ofPattern("HH:mm:ss")
                            .format((new Date()).toInstant()
                                    .atZone(ZoneId.systemDefault())))
                    .build();
            log.info(orderRepository.save(order).toString());
        }
    }

    @GetMapping("/orders")
    public List<Order> getOrdersByUser() {
        User user = usersService.getUserFromToken();
        return orderRepository.findByUser(user).get();
    }

    @DeleteMapping("/order")
    public void deleteOrder(@RequestParam int dishId) {
        orderService.removeOrder(dishId);
        System.out.println("Was deleted");
    }

}
