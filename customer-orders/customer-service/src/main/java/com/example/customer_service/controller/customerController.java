package com.example.customer_service.controller;

import com.example.customer_service.model.customerPojo;
import com.example.customer_service.model.orderPojo;
import com.example.customer_service.repo.customerRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class customerController {

    public static final Logger LOG = LoggerFactory.getLogger(customerController.class);

    @Autowired
    customerRepo cr;

    @GetMapping("/customer")
    public List<customerPojo> getAllCustomers(){
        LOG.info("in getAllCustomers");
        return cr.findAll();
    }

    @GetMapping("/customer/{cid}")
    @CircuitBreaker(name="ciremp", fallbackMethod = "custFallback")
    public customerPojo getAcustomer(@PathVariable("cid") long custId){
        LOG.info("in getACustomer");
        customerPojo custPojo = cr.findById(custId).get();
        RestClient restClient = RestClient.create();
        List<orderPojo> allOrders = restClient
                .get()
                .uri("http://localhost:8082/api/order/customer/" + custId)
                .retrieve()
                .body(List.class);
        custPojo.setAllorders(allOrders);
        return custPojo;
    }

    public customerPojo custFallback(){
        return new customerPojo(0,"fallback",null);

    }

    @PostMapping("/customer")
    public customerPojo addCustomer(@RequestBody customerPojo newCust){
        LOG.info("in addCustomer");
        return cr.saveAndFlush(newCust);
    }

}

