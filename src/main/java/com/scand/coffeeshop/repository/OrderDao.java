package com.scand.coffeeshop.repository;

import com.scand.coffeeshop.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderDao {

    Order get(Long id) throws Exception;

    Long save(Order order) throws Exception;

    List<Order> list();
}
