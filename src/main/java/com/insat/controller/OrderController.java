package com.insat.controller;


import com.insat.model.CartItem;
import com.insat.model.Order;
import com.insat.model.Person;
import com.insat.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @CrossOrigin(origins = "*")
    @PostMapping("/new/{client_id}")
    public  ResponseEntity<Order> addOrder(@Valid @RequestBody Order order, @PathVariable Long client_id) {
        Order orderRes = orderService.createOrder(order,client_id);
        if(orderRes == null) return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(orderRes,HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/valid/{order_id}")
    public ResponseEntity<Order> validateOrder(@PathVariable Long order_id) {
        if (orderService.confirmOrder(order_id) != null) {
            return new ResponseEntity<>(orderService.confirmOrder(order_id), HttpStatus.OK);
        } else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/cart/add/{order_id}")
    public ResponseEntity<CartItem> addItemToCart(@Valid @RequestBody CartItem cartItem,
                                                  @PathVariable Long order_id){
        CartItem cart = orderService.addItemToCart(cartItem,order_id);
        if(cart == null)return  new ResponseEntity<>(HttpStatus.LOCKED);
        else return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{order_id}")
     public ResponseEntity<List<CartItem>> getOrderItems(@PathVariable Long order_id) {
         List<CartItem> cartItemsList = orderService.getOrderCartItems(order_id);
         if(cartItemsList == null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(cartItemsList,HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/cart/delete/{id_order}/{id_book}")
    public ResponseEntity deleteItemFromCart(@PathVariable Long id_order,
                                   @PathVariable Long id_book){
        if(orderService.deleteItemFromCart(id_order,id_book)) return  new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.LOCKED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/order/{order_id}")
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable Long order_id) {
        Optional<Order> order = orderService.getOrderById(order_id);
        if (!order.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(order,HttpStatus.OK);
    }



}
