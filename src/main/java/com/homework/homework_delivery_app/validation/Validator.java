package com.homework.homework_delivery_app.validation;

import com.homework.homework_delivery_app.dto.FoodDto;
import com.homework.homework_delivery_app.dto.RestaurantDto;
import com.homework.homework_delivery_app.model.Food;

import java.util.List;

public class Validator {
    public static void restaurantValidator(RestaurantDto requestDto) {
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        if ( minOrderPrice < 1000 || minOrderPrice > 100000){
            throw new IllegalArgumentException("최수 주문 가격의 허용 범위는 1,000원 ~ 100,000원 입니다.");
        }
        if (minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("최소 주문 가격은 100원 단위로 입력 가능합니다.");
        }
        if ( deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalArgumentException("기본 배달비의 허용 범위는 0원 ~ 10,000원 입니다.");
        }
        String deliveryFeeStr = Integer.toString(deliveryFee);
        if (deliveryFee % 500 != 0){
            throw new IllegalArgumentException("기본 배달비는 500원 단위로 입력 가능합니다.");
        }
    }

    public static void foodValidator(FoodDto foodDto) {
        int foodPrice = foodDto.getPrice();
        if (foodPrice < 100 || foodPrice > 1000000){
            throw new IllegalArgumentException("음식 가격의 허용 범위는 100원 ~ 1,000,000원 입니다.");
        }
        if (foodPrice % 100 != 0){
            throw new IllegalArgumentException("음식 가격은 100원 단위로 입력 가능합니다.");
        }
    }
}
