package com.homework.homework_delivery_app.repository;

import com.homework.homework_delivery_app.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // 한번에 중복된 음식인지 판단하기 위해 메뉴id와 음식 이름까지 받아온다.
    Optional<Food> findByNameAndMenuId(String name, Long id);
}
