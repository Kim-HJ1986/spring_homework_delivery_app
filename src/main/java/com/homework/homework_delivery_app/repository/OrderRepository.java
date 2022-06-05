package com.homework.homework_delivery_app.repository;

import com.homework.homework_delivery_app.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
