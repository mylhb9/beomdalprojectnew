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
    private final OrderRepository orderRepository;

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
            if(foodResponseDtos.get(i).getQuantity()<1 || foodResponseDtos.get(i).getQuantity()>100) {
                throw new IllegalStateException("1~100 아님");
            }

            totalPrice += foodResponseDtos.get(i).getPrice() * foodResponseDtos.get(i).getQuantity();
            foodResponseDtos.get(i).setPrice(foodResponseDtos.get(i).getPrice() * foodResponseDtos.get(i).getQuantity());

        }
        if(totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalStateException("최소주문가격은 시켜주셈");
        }
        totalPrice = totalPrice + restaurant.getDeliveryFee();


        System.out.println(totalPrice);
        OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant.getName(), foodResponseDtos, restaurant.getDeliveryFee(), totalPrice);

        Order order = new Order(restaurant, totalPrice);


        List<Menu> menus = new ArrayList<>();
        for(int i=0; i<foodResponseDtos.size(); i++) {
            menus.add(new Menu(foodResponseDtos.get(i).getQuantity(), order, foodRepository.findByName(foodResponseDtos.get(i).getName())));
        }
        System.out.println(menus);
        menuRepository.saveAll(menus);
        return orderResponseDto;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Menu> menuList = menuRepository.findAll();
        System.out.println(menuList);
        List<Order> orderList = orderRepository.findAll();
        System.out.println(orderList);
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        List<FoodResponseDto> foodResponseDtos = new ArrayList<>();

        for(int i=0; i< menuList.size(); i++) {


            foodResponseDtos.add(new FoodResponseDto(menuList.get(i).getFood().getName(), menuList.get(i).getQuantity(), menuList.get(i).getFood().getPrice() * menuList.get(i).getQuantity()));

        }

        for (int i=0; i<orderList.size(); i++) {
            orderResponseDtos.add(new OrderResponseDto(orderList.get(i).getRestaurant().getName(),
                    foodResponseDtos,
                    orderList.get(i).getRestaurant().getDeliveryFee()
                    ,orderList.get(i).getTotalprice()));
        }




        return orderResponseDtos;
    }
}
