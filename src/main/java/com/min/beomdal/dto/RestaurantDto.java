package com.min.beomdal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@ToString
@Getter
@Setter
@AllArgsConstructor
public class RestaurantDto {

    private String name;


    private int minOrderPrice;


    private int deliveryFee;
}
