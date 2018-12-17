package com.insat.repository;

import com.insat.model.CartItem;
import com.insat.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findCartItemsByOrder(Order order);
    CartItem findCartItemByBook_id(Long book_id);
}
