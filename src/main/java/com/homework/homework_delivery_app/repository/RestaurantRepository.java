package com.homework.homework_delivery_app.repository;

import com.homework.homework_delivery_app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
//    Optional<Restaurant> finByName(String name);
}
