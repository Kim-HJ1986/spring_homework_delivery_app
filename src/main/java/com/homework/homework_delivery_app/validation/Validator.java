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
        String minOrderPriceStr = Integer.toString(minOrderPrice);
        minOrderPriceStr = minOrderPriceStr.substring(minOrderPriceStr.length()-2);
        if (!minOrderPriceStr.equals("00")){
            throw new IllegalArgumentException("최소 주문 가격은 100원 단위로 입력 가능합니다.");
        }
        if ( deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalArgumentException("기본 배달비의 허용 범위는 0원 ~ 10,000원 입니다.");
        }
        String deliveryFeeStr = Integer.toString(deliveryFee);
        if(deliveryFeeStr.length() >= 3){
            deliveryFeeStr = deliveryFeeStr.substring(deliveryFeeStr.length()-3);
            if(!(deliveryFeeStr.equals("000") || deliveryFeeStr.equals("500"))){
                throw new IllegalArgumentException("기본 배달비는 500원 단위로 입력 가능합니다.");
            }
        }else if(!deliveryFeeStr.equals("0")){
            throw new IllegalArgumentException("기본 배달비는 500원 단위로 입력 가능합니다.");
        }

    }

    public static void foodExistValidator(List<Food> existFoods, FoodDto foodDto) {
        for( Food food : existFoods){
            if(food.getName().equals(foodDto.getName())){
                throw new IllegalArgumentException("중복된 음식 이름은 등록할 수 없습니다. 중복된 음식: " + foodDto.getName());
            }
        }
    }

    public static void foodValidator(FoodDto foodDto) {
        if (foodDto.getPrice() < 100 || foodDto.getPrice() > 1000000){
            throw new IllegalArgumentException("음식 가격의 허용 범위는 100원 ~ 1,000,000원 입니다.");
        }
        String foodPriceStr = Integer.toString(foodDto.getPrice());
        foodPriceStr = foodPriceStr.substring(foodPriceStr.length()-2);
        if (!foodPriceStr.equals("00")){
            throw new IllegalArgumentException("음식 가격은 100원 단위로 입력 가능합니다.");
        }
    }
}
