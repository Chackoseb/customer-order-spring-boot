package com.example.customer_service.repo;

import com.example.customer_service.model.customerPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepo extends JpaRepository<customerPojo,Long> {

}
