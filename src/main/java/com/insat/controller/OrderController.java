package com.insat.controller;


import com.insat.model.Order;
import com.insat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/new")
    public  ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(orderService.addOrder(order),HttpStatus.OK);
    }

    @GetMapping("/valid/{id}")
    public ResponseEntity<Order> validateOrder(@PathVariable Long id) {
        if (orderService.confirmOrder(id) != null) {
            return new ResponseEntity<>(orderService.confirmOrder(id), HttpStatus.OK);
        } else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
