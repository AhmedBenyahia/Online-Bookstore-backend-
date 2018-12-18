package com.insat.repository;

import com.insat.model.Order;
import com.insat.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long > {
    Order findOrderByStatusAndClient(Integer status, Person client);
}
