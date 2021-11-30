package com.min.beomdal.controller;


import com.min.beomdal.dto.FoodDto;
import com.min.beomdal.entity.Food;
import com.min.beomdal.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registFood(@PathVariable("restaurantId") Long id, @RequestBody List<FoodDto> foodList) {
        foodService.saveFood(id, foodList);
    }
    //음식 리스트 호출
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable("restaurantId") Long id) {
        return foodService.getAllFoods(id);
    }



}
