package com.lighter.granate.controller;

import com.lighter.granate.dto.CreateDishDTO;
import com.lighter.granate.entity.Dish;
import com.lighter.granate.repository.DishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final DishRepository dishRepository;

    public ProductController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
//Not Auth
    @GetMapping("/products")
    public List<Dish> getProducts() {
        return dishRepository.findAll();
    }
//Auth
    @GetMapping("/product")
    public Dish getProduct(@RequestParam int id) {
        return dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }
    //Authorized
    @PostMapping("/product")
    public void addProduct(@RequestBody CreateDishDTO dto) {
        Dish dish = Dish.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .img(dto.getImg())
                .build();
        dishRepository.save(dish);
    }
}
