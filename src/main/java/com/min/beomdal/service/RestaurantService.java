package com.min.beomdal.service;


import com.min.beomdal.dto.RestaurantDto;
import com.min.beomdal.entity.Restaurant;
import com.min.beomdal.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    //음식점 저장 메소드
    public Restaurant saveRestaurant(RestaurantDto restaurantDto) {
        if(restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100000) {
            throw new IllegalStateException("허용 값 범위를 벗어났습니다.");
        }
        if(restaurantDto.getMinOrderPrice()%100>0) {
            throw new IllegalStateException("100원 단위가 아닙니다");
        }
        if(restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10000) {
            throw new IllegalStateException("허용 값 범위를 벗어났습니다.");
        }
        if(restaurantDto.getMinOrderPrice()%500>0) {
            throw new IllegalStateException("500원 단위가 아닙니다");
        }
        Restaurant restaurant = new Restaurant(restaurantDto);
        return restaurantRepository.save(restaurant);
    }

    //음식점 조회 메소드
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }



}
