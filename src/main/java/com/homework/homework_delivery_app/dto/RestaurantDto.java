package com.homework.homework_delivery_app.dto;

import lombok.Data;

@Data
public class RestaurantDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
