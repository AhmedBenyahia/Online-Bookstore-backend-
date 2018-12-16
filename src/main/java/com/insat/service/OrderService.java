package com.insat.service;


import com.insat.model.Order;

public interface OrderService {
    Order addOrder(Order order) ;
    Order confirmOrder(Long id);

}
