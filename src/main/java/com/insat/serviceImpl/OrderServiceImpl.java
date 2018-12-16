package com.insat.serviceImpl;

import com.insat.model.Order;
import com.insat.repository.OrderRepository;
import com.insat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order confirmOrder(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if( order.isPresent()){
            order.get().setConfirmation(true);
            orderRepository.save(order.get());
            return  order.get();
        } else {
            return null;
        }
    }
}
