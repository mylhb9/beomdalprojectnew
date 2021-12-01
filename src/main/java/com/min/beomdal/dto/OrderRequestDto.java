package com.min.beomdal.dto;


import com.min.beomdal.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodRequestDto> foods;

}
