package com.min.beomdal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.min.beomdal.dto.FoodDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurantId")
    private Restaurant restaurant;

    public Food(FoodDto foodDto) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();


    }
    public Food(FoodDto foodDto, Restaurant restaurant) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = restaurant;
    }

}