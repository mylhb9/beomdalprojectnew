package com.min.beomdal.service;


import com.min.beomdal.dto.FoodDto;
import com.min.beomdal.entity.Food;
import com.min.beomdal.entity.Restaurant;
import com.min.beomdal.repository.FoodRepository;
import com.min.beomdal.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    //음식저장 (레스토랑 정보 포함)
    @Transactional
    public List<Food> saveFood(Long id, List<FoodDto> foodList) {
        System.out.println(foodList);
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("레스토랑 없다")
        );
        List<Food> foods = new ArrayList<>();
        List<Food> foods1 = new ArrayList<>();
        for(int i=0; i<foodList.size(); i++) {
            foods.add(new Food(foodList.get(i),restaurant));
        }

        foods1=getAllFoods(id);

        // 같은 레스토랑에 기존에 저장된 음식명 중복 예외처리
        for(int j=0; j<foods1.size();j++) {
            for(int i=0; i< foods.size(); i++) {
                if(foods1.get(j).getName().equals(foods.get(i).getName())) {
                    throw new IllegalStateException("해당 음식점에 같은 음식이 존재합니다.");
                }

            }
        }
        for(int j=0; j<foods.size()-1;j++) {
            for(int i=j+1; i<foods.size(); i++) {
                if(foods.get(j).getName().equals(foods.get(i).getName())) {
                    throw new IllegalStateException("집보내줘 제발");
                }
            }
        }

        for(int i=0; i<foods.size(); i++) {
            if(foods.get(i).getPrice() < 100 || foods.get(i).getPrice() > 1000000) {
                throw new IllegalStateException("허용 값 범위를 벗어났습니다.");
            }
            if(foods.get(i).getPrice()%100>0) {
                throw new IllegalStateException("100원 단위가 아닙니다");
            }
        }
        return foodRepository.saveAll(foods);



//        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
//                ()->new IllegalArgumentException("해당하는 음식점이 존재하지 않습니다."));
//        List<FoodDto> foods = getAllFoods(id);
//        // 같은 레스토랑에 음식명 중복 예외처리
//        for(int j=0; j<foods.size();j++) {
//            for(int i=0; i< foodList.size(); i++) {
//                if(foods.get(j).getName().equals(foodList.get(i).getName())) {
//                    throw new IllegalStateException("해당 음식점에 같은 음식이 존재합니다.");
//                }
//
//            }
//        }
//        System.out.println("restaurant의 값: " +restaurant);
//        int cnt = foodList.size();
//        for (int i=0; i<cnt; i++) {
//            Restaurant restaurant1 = restaurant;
//            foodList.get(i).setRestaurant(restaurant1);
//        }
//        for(int i=0; i<foodList.size(); i++) {
//            if(foodList.get(i).getPrice() < 100 || foodList.get(i).getPrice() > 1000000) {
//                throw new IllegalStateException("허용 값 범위를 벗어났습니다.");
//            }
//            if(foodList.get(i).getPrice()%100>0) {
//                throw new IllegalStateException("100원 단위가 아닙니다");
//            }
//        }
//        System.out.println("foodList의 값: " +foodList);
//        System.out.println(foodList);
//
//
//        return foodRepository.saveAll(foodList);

    }
    //음식 리스트 출력
    @Transactional
    public List<Food> getAllFoods(Long id) {

        return foodRepository.findByRestaurant_Id(id);
    }

}
