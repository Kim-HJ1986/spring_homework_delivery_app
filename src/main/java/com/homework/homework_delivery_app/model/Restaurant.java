package com.homework.homework_delivery_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homework.homework_delivery_app.dto.RestaurantDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Restaurant {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String name;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private int minOrderPrice;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private int deliveryFee;

    @JsonIgnore
    @OneToOne
    private Menu menu;

    @JsonIgnore
    @OneToMany
    private List<Orders> orders;

    public Restaurant(RestaurantDto restaurantDto){
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }
}
