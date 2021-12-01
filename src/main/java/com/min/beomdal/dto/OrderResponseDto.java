package com.min.beomdal.dto;


import com.min.beomdal.entity.Food;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodResponseDto> foodList;
    private int deliveryFee;
    private int totalPrice;





}
