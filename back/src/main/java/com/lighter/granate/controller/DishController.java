package com.lighter.granate.controller;

import com.lighter.granate.dto.CreateDishDTO;
import com.lighter.granate.dto.UpdateDishDTO;
import com.lighter.granate.entity.Dish;
import com.lighter.granate.repository.DishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class DishController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final DishRepository dishRepository;


    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping("/dish")
    public Dish getDishById(@RequestParam int id) {
        return dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }
    @PostMapping("/admin/dish")
    public void createDish(@RequestBody CreateDishDTO dishDto) {
        Dish dish = Dish.builder()
                .title(dishDto.getTitle())
                .price(dishDto.getPrice())
                .img(dishDto.getImg())
                        .build();
        log.info(dishRepository.save(dish).toString());
    }
    @GetMapping("/dishes")
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }
    @DeleteMapping("/admin/dish")
    public void deleteDish(@RequestParam int id) {
        dishRepository.deleteById(id);
    }
    @PutMapping("/admin/dish")
    public String updateDish(@RequestBody UpdateDishDTO dishDto) {
        if (!dishRepository.existsById(dishDto.getId())) {
            return "Dish don`t exists.";
        }
        String img = dishRepository.getById(dishDto.getId()).getImg();
        Dish dish = Dish.builder()
                .id(dishDto.getId())
                .title(dishDto.getTitle())
                .price(dishDto.getPrice())
                .img(img)
                .build();
        return dishRepository.save(dish).toString();
    }

}