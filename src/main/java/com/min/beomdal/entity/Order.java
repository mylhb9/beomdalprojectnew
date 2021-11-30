package com.min.beomdal.entity;


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
@Table(name="orderss")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
//
//    @Column
//    private String restaurantName;
//
//    @Column
//    private Long deliveryFee;
//
//    @Column
//    private Long totalPrice;
//
//    @Column
//    private Long restaurantId;
//
//
//
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
//    private List<Food> foodList;
//








}
