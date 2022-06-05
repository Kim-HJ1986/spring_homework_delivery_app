package com.homework.homework_delivery_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homework.homework_delivery_app.dto.FoodDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Food {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String name;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private int price;

    @JsonIgnore
    @ManyToMany
    private List<Menu> menus;

    @JsonIgnore
    @ManyToMany
    private List<Orders> orders;

    public Food(FoodDto foodDto) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}
