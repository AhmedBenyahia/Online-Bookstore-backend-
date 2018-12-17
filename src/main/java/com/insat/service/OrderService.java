package com.insat.service;


import com.insat.model.CartItem;
import com.insat.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order) ;
    CartItem addItemToCart(CartItem item) ;
    List<CartItem> getOrderCartItems(Long id_order);
    void deleteItemFromCart(Long id_order,Long id_book);
    Order confirmOrder(Long id);


}
