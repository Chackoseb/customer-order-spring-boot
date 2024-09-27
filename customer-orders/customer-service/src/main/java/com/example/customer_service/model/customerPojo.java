package com.example.customer_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class customerPojo {
    @Id
    private long custId;
    private String custName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<orderPojo> allorders;

}
