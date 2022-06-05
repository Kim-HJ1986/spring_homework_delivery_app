package com.homework.homework_delivery_app.repository;

import com.homework.homework_delivery_app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
