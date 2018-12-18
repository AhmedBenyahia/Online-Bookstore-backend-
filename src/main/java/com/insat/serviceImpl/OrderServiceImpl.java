package com.insat.serviceImpl;

import com.insat.model.CartItem;
import com.insat.model.Order;
import com.insat.model.Person;
import com.insat.repository.BookRepository;
import com.insat.repository.CartItemRepository;
import com.insat.repository.OrderRepository;
import com.insat.repository.PersonRepository;
import com.insat.service.OrderService;
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

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Order createOrder(Order order, Long client_id) {
        Person client = personRepository.findById(client_id).get();
        order.setClient(client);
        Order oldOrder = orderRepository.findOrderByStatusAndClient(
                    Order.NOTCONFIRMED,
                    client
            );
            if(oldOrder != null)return oldOrder;
            else return orderRepository.save(order);
    }

    @Override
    public CartItem addItemToCart(CartItem item, Long id) {
        Order order = orderRepository.findById(id).get();
        if(order.getStatus().equals(Order.NOTCONFIRMED)) {
            item.setPrice(bookRepository.findById(item.getBookid())
                    .get().getPrice() * item.getQuantity());
            item.setOrder(order);
            order.updateTotalCost(item.getPrice());
            return cartItemRepository.save(item);
        }else return null;
    }

    @Override
    public List<CartItem> getOrderCartItems(Long id_order) {
        Optional<Order> order = orderRepository.findById(id_order);
        return order.map(order1
                -> cartItemRepository.findCartItemsByOrder(order1))
                .orElse(null);
    }

    @Override
    public boolean deleteItemFromCart(Long id_order, Long id_book) {
       Optional<Order> order = orderRepository.findById(id_order);
        if(!order.isPresent())return false;
        if(order.get().getStatus().equals(Order.NOTCONFIRMED)) {
            CartItem cartItem = cartItemRepository.findCartItemByBookidAndOrder(id_book, order.get());
            order.get().updateTotalCost(cartItem.getPrice() * -1);
            cartItemRepository.delete(cartItem);
            return  true;
        }return  false;

    }

    public Order confirmOrder(Long order_id) {

        Optional<Order> order = orderRepository.findById(order_id);
        if( order.isPresent()){
            order.get().setStatus(Order.INPROGRESS);
            orderRepository.save(order.get());
            return  order.get();
        } else {
            return null;
        }
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
