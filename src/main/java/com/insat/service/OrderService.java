package com.insat.service;


import com.insat.model.CartItem;
import com.insat.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order,Long client_id) ;
    CartItem addItemToCart(CartItem item, Long id) ;
    List<CartItem> getOrderCartItems(Long id_order);
    boolean deleteItemFromCart(Long id_order,Long id_book);
    Order confirmOrder(Long id);
    Optional<Order> getOrderById(Long id);


}
