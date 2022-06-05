package com.homework.homework_delivery_app.model;

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
public class Menu {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToOne
    private Restaurant restaurant;

    @OneToMany
    private List<Food> foods = new ArrayList<>();

    public Menu(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void registerFood(Food food) {
        this.foods.add(food);
    }
}
