package com.homework.homework_delivery_app.controller;

import com.homework.homework_delivery_app.dto.RestaurantDto;
import com.homework.homework_delivery_app.model.Restaurant;
import com.homework.homework_delivery_app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantDto requestDto){
        return restaurantService.registerRestaurant(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantList(){
        return restaurantService.getRestaurantList();
    }
}
