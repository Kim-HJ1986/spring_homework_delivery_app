package com.homework.homework_delivery_app.dto;

import com.homework.homework_delivery_app.model.Food;
import com.homework.homework_delivery_app.model.Orders;
import com.homework.homework_delivery_app.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;
    List<FoodOrderResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderResponseDto(Orders order){
        this.foods = new ArrayList<>();
        this.restaurantName = order.getRestaurant().getName();
        this.deliveryFee = order.getRestaurant().getDeliveryFee();

    }
    public OrderResponseDto(Restaurant restaurant){
        this.foods = new ArrayList<>();
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();

    }
}
