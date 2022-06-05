package com.homework.homework_delivery_app.service;

import com.homework.homework_delivery_app.dto.FoodDto;
import com.homework.homework_delivery_app.model.Food;
import com.homework.homework_delivery_app.model.Menu;
import com.homework.homework_delivery_app.model.Restaurant;
import com.homework.homework_delivery_app.repository.FoodRepository;
import com.homework.homework_delivery_app.repository.MenuRepository;
import com.homework.homework_delivery_app.repository.RestaurantRepository;
import com.homework.homework_delivery_app.validation.Validator;
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
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다."));

        List<Food> foodList = new ArrayList<>();
        for (FoodDto foodDto : foodDtoList){
            // 음식 이름 중복 여부 체크
            if(menu.getFoods().size() != 0){
                List<Food> existFoods = menu.getFoods();
                Validator.foodExistValidator(existFoods, foodDto);
            }

            Validator.foodValidator(foodDto);
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
