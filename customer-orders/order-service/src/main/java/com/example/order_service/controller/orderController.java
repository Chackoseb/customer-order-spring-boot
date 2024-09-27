package com.example.order_service.controller;

import com.example.order_service.model.orderPojo;
import com.example.order_service.repo.orderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class orderController {

    @Autowired
    orderRepo or;

    public static final Logger LOG = LoggerFactory.getLogger(orderController.class);

    @GetMapping("/order")
    public List<orderPojo> getAllorders() {
        LOG.info("in getAllorders");
        return or.findAll();
    }

    @GetMapping("/order/{oid}")
    public orderPojo getAOrder(@PathVariable("oid") long orderId) {
        LOG.info("in getAOrder");
        return or.findById(orderId).get();
    }

    @PostMapping("/order")
    public orderPojo addOrder(@RequestBody orderPojo newOrder) {
        LOG.info("in addOrder");
        return or.saveAndFlush(newOrder);
    }

    @GetMapping("/order/customer/{cid}")
    public List<orderPojo> getAllOrderByCustomer(@PathVariable long cid){
        return or.findByOrderCustId(cid);
    }
}

