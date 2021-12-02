package com.min.beomdal.dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class FoodResponseDto {
    private String name;
    private int quantity;
    private int price;



}