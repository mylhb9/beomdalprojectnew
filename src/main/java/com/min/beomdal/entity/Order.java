package com.min.beomdal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.min.beomdal.dto.OrderDto;
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
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @Column
    private int totalprice;

    public Order(Restaurant restaurant, int totalprice) {
        this.restaurant = restaurant;
        this.totalprice = totalprice;
    }



}
