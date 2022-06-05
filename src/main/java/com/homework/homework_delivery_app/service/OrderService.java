package com.homework.homework_delivery_app.service;

import com.homework.homework_delivery_app.dto.FoodOrderRequestDto;
import com.homework.homework_delivery_app.dto.FoodOrderResponseDto;
import com.homework.homework_delivery_app.dto.OrderRequestDto;
import com.homework.homework_delivery_app.dto.OrderResponseDto;
import com.homework.homework_delivery_app.model.Food;
import com.homework.homework_delivery_app.model.Orders;
import com.homework.homework_delivery_app.model.Restaurant;
import com.homework.homework_delivery_app.repository.FoodRepository;
import com.homework.homework_delivery_app.repository.OrderRepository;
import com.homework.homework_delivery_app.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderService(OrderRepository orderRepository,
                        FoodRepository foodRepository,
                        RestaurantRepository restaurantRepository){
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }
    @Transactional
    public OrderResponseDto requestOrder(OrderRequestDto requestDto) {
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다."));
        Orders order = new Orders(restaurant);

        OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant);
        int sum = orderResponseDto.getDeliveryFee();

//        order.setRestaurant(restaurant);
        for(FoodOrderRequestDto foodDto : requestDto.getFoods()){
            if(foodDto.getQuantity() < 1 || foodDto.getQuantity() > 100){
                throw new IllegalArgumentException("주문 가능 수량의 범위는 1 ~ 100 입니다.");
            }
            order.getFoodQuantity().put(foodDto.getId(), foodDto.getQuantity());
            Food food = foodRepository.findById(foodDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지 않습니다."));
            order.getFoods().add(food);
            FoodOrderResponseDto foodOrderResponseDto
                    = new FoodOrderResponseDto(food.getName(), foodDto.getQuantity(), foodDto.getQuantity() * food.getPrice());

            orderResponseDto.getFoods().add(foodOrderResponseDto);
            sum += foodOrderResponseDto.getPrice();
        }
        if(sum - orderResponseDto.getDeliveryFee() < restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("최소 주문 가격을 넘겨 주세요!!");
        }
        orderResponseDto.setTotalPrice(sum);
        orderRepository.save(order);

        return orderResponseDto;
    }

    public List<OrderResponseDto> getOrders() {
        List<Orders> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for(Orders order : orders){
            OrderResponseDto responseDto = new OrderResponseDto(order);
            int sum = responseDto.getDeliveryFee();
            List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

            for(Food food : order.getFoods()){
                int quantity = order.getFoodQuantity().get(food.getId());
                FoodOrderResponseDto foodOrderResponseDto
                        = new FoodOrderResponseDto(food.getName(), quantity, quantity * food.getPrice());
                foodOrderResponseDtoList.add(foodOrderResponseDto);
                sum += foodOrderResponseDto.getPrice();
            }
            responseDto.setTotalPrice(sum);
            responseDto.getFoods().addAll(foodOrderResponseDtoList);
            orderResponseDtoList.add(responseDto);
        }
        return orderResponseDtoList;
    }
}
