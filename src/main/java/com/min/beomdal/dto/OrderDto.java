package com.min.beomdal.dto;

import com.min.beomdal.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Restaurant restaurant;

}
