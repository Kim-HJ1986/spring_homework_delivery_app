package com.homework.homework_delivery_app.controller;

import com.homework.homework_delivery_app.dto.FoodDto;
import com.homework.homework_delivery_app.model.Food;
import com.homework.homework_delivery_app.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public List<Food> registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtoList){
        return foodService.registerFood(restaurantId, foodDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }
}
