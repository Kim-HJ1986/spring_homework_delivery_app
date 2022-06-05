package com.homework.homework_delivery_app.repository;

import com.homework.homework_delivery_app.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByName(String name);

    Food findByName(String name);

}
