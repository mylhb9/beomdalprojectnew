package com.min.beomdal.service;


import com.min.beomdal.dto.FoodRequestDto;
import com.min.beomdal.dto.FoodResponseDto;
import com.min.beomdal.dto.OrderRequestDto;
import com.min.beomdal.dto.OrderResponseDto;
import com.min.beomdal.entity.Food;
import com.min.beomdal.entity.Menu;
import com.min.beomdal.entity.Order;
import com.min.beomdal.entity.Restaurant;
import com.min.beomdal.repository.FoodRepository;
import com.min.beomdal.repository.MenuRepository;
import com.min.beomdal.repository.OrderRepository;
import com.min.beomdal.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        System.out.println(orderRequestDto);
        //주문서에서 받아온 음식점 정보
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                ()->new IllegalArgumentException("해당 음식점 없다")
        );
        //주문서에서 받아온 음식점에 해당하는 저장된 음식물 리스트
        List<Food> foodList = foodRepository.findByRestaurant_Id(orderRequestDto.getRestaurantId());
        List<FoodResponseDto> foodResponseDtos = new ArrayList<>();
        int totalPrice = 0;
        for(int i=0; i< foodList.size(); i++) {
            for (int j=0; j< orderRequestDto.getFoods().size(); j++) {
                if(foodList.get(i).getId().equals(orderRequestDto.getFoods().get(j).getId())) {
                    foodResponseDtos.add(new FoodResponseDto(foodList.get(i).getName(),orderRequestDto.getFoods().get(j).getQuantity(),foodList.get(i).getPrice()));
                }
            }

        }
        for(int i=0; i<foodResponseDtos.size(); i++) {
            totalPrice += foodResponseDtos.get(i).getPrice() * foodResponseDtos.get(i).getQuantity();
        }
        System.out.println(totalPrice);
        OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant.getName(), foodResponseDtos, restaurant.getDeliveryFee(), totalPrice);

        Order order = new Order(restaurant);





        List<Menu> menus = new ArrayList<>();
        for(int i=0; i<foodResponseDtos.size(); i++) {
            menus.add(new Menu(foodResponseDtos.get(i).getQuantity(), order, foodRepository.findByName(foodResponseDtos.get(i).getName())));
        }
        System.out.println(menus);
        menuRepository.saveAll(menus);
        return orderResponseDto;
    }


}
