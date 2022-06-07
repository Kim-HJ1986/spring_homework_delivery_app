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

        List<Food> foodList = new ArrayList<>();
        for (FoodDto foodDto : foodDtoList){
            // 음식 이름 중복 여부 체크
            if(menu.getFoods().size() != 0){
                List<Food> existFoods = menu.getFoods();
                Validator.foodExistValidator(existFoods, foodDto);
            }

            // 음식 관련 validation check
            Validator.foodValidator(foodDto);
            Food food = new Food(foodDto);

            //menu.getFoods().add(food); // 메뉴에 저장되는 readOnly 상대 객체도 저장을 해줘야 한다. 아래에서 처리!
            food.setMenu(menu); // setMenu를 할 때 menu의 foods에 해당 food를 add 한다! (연관관계 편의 메서드)

            foodRepository.save(food); // 음식 객체 save
            foodList.add(food);
        }
        return foodList;
    }

    public List<Food> getFoods(Long restaurantId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId);
        return menu.getFoods();
    }
}
