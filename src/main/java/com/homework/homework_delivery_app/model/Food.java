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
    @ManyToOne
//    @JoinColumn(name="MENU_ID") -> 안해줘도 ToOne이기 때문에 자동으로 FK 컬럼이 생성된다.
    private Menu menu;

    public Food(FoodDto foodDto) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.getFoods().add(this);
    }
}
