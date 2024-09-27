package com.example.order_service.repo;

import com.example.order_service.model.orderPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface orderRepo extends JpaRepository<orderPojo,Long> {
    List<orderPojo> findByOrderCustId(long custId);
}
