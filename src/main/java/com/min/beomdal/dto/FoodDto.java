package com.min.beomdal.dto;


import com.min.beomdal.entity.Food;
import com.min.beomdal.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class FoodDto {
    private String name;
    private int price;
}
