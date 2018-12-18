package com.insat.serviceImpl;

import com.insat.model.CartItem;
import com.insat.model.Order;
import com.insat.model.Person;
import com.insat.repository.CartItemRepository;
import com.insat.repository.OrderRepository;
import com.insat.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Order createOrder(Order order) {
            return orderRepository.save(order);
    }

    @Override
    public CartItem addItemToCart(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public List<CartItem> getOrderCartItems(Long id_order) {
        return cartItemRepository.findCartItemsByOrder(
                orderRepository.findById(id_order).get()
        );
    }

    @Override
    public void deleteItemFromCart(Long id_order, Long id_book) {
        orderRepository.findById(id_order).get()
                .deleteItemsFromCart(
                        cartItemRepository.findCartItemByBookid(id_book)
                );
    }

    public Order confirmOrder(Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if( order.isPresent()){
            order.get().setStatus(Order.INPROGRESS);
            orderRepository.save(order.get());
            return  order.get();
        } else {
            return null;
        }
    }
}
