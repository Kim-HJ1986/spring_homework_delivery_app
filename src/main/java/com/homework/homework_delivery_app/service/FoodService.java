package com.homework.homework_delivery_app.service;

import com.homework.homework_delivery_app.dto.FoodDto;
import com.homework.homework_delivery_app.model.Food;
import com.homework.homework_delivery_app.model.Menu;
import com.homework.homework_delivery_app.model.Restaurant;
import com.homework.homework_delivery_app.repository.FoodRepository;
import com.homework.homework_delivery_app.repository.MenuRepository;
import com.homework.homework_delivery_app.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public FoodService(FoodRepository foodRepository,
                       RestaurantRepository restaurantRepository,
                       MenuRepository menuRepository){
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }
    @Transactional
    public List<Food> registerFood(Long restaurantId, List<FoodDto> foodDtoList) {

        Menu menu = menuRepository.findByRestaurantId(restaurantId);

        List<Food> foodList = new ArrayList<>();
        for (FoodDto foodDto : foodDtoList){
            Food food = new Food(foodDto);
            menu.registerFood(food);
            foodList.add(food);
        }
        return foodRepository.saveAll(foodList);
    }

    public List<Food> getFoods(Long restaurantId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        return menu.getFoods();
    }
}
