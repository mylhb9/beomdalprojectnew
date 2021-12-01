package com.min.beomdal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="order_id")
    private Order order;

    @Column
    private int quantity;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="food_id")
    private Food food;

    @Column
    private String foodname;

    @Column
    private int price;


    public Menu(int quantity, Order order,Food food) {
        this.quantity = quantity;
        this.order = order;
        this.food = food;
        this.foodname = food.getName();
        this.price = food.getPrice();

    }



}