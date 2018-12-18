package com.insat.repository;

import com.insat.model.CartItem;
import com.insat.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findCartItemsByOrder(Order order);
    CartItem findCartItemByBookidAndOrder(Long bookid, Order order);
}
