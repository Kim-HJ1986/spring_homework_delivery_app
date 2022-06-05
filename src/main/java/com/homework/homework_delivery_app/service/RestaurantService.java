package com.homework.homework_delivery_app.service;

import com.homework.homework_delivery_app.dto.RestaurantDto;
import com.homework.homework_delivery_app.model.Menu;
import com.homework.homework_delivery_app.model.Restaurant;
import com.homework.homework_delivery_app.repository.MenuRepository;
import com.homework.homework_delivery_app.repository.RestaurantRepository;
import com.homework.homework_delivery_app.validation.Validator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuRepository menuRepository){
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }
    @Transactional
    public Restaurant registerRestaurant(RestaurantDto requestDto) {
        // validation check
        Validator.restaurantValidator(requestDto);

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        Menu menu = new Menu(restaurant);
        menuRepository.save(menu);
        return restaurant;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantRepository.findAll();
    }
}
