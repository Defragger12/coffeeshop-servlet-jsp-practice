package com.scand.coffeeshop.repository;

import com.scand.coffeeshop.domain.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderItemDao {

    void save(List<OrderItem> orderItems) throws Exception;

    List<OrderItem> list();
}
