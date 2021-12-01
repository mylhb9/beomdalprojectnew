package com.min.beomdal.repository;


import com.min.beomdal.dto.OrderResponseDto;
import com.min.beomdal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
