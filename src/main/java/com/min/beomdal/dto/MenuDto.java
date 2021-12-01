package com.min.beomdal.dto;

import com.min.beomdal.entity.Food;
import com.min.beomdal.entity.Order;
import com.min.beomdal.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class MenuDto {
    private int quantity;
    private Food food;
    private Order order;

}
