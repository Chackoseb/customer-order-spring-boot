package com.example.customer_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class orderPojo {
    @Id
    private long orderId;
    private String orderName;
    private long orderCustId;

}
