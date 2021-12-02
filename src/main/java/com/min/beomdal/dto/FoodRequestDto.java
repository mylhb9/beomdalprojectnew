package com.min.beomdal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class FoodRequestDto {
    private Long id;
    private int quantity;
}
