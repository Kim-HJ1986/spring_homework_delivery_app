package com.homework.homework_delivery_app.repository;

import com.homework.homework_delivery_app.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByRestaurantId(Long restaurantId);
}
