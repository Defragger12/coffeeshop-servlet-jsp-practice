package com.scand.coffeeshop.repository.sql;

import com.scand.coffeeshop.domain.OrderItem;
import com.scand.coffeeshop.repository.OrderItemDao;
import com.scand.coffeeshop.repository.sql.util.SqlConnectionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class SqlOrderItemDao implements OrderItemDao {

    @Override
    public void save(List<OrderItem> orderItems) throws Exception {

        Connection connection = SqlConnectionManager.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO orderitem (quantity, coffee_id, order_id) VALUES (?, ?, ?)"
        );

        for (OrderItem orderItem : orderItems) {
           ps.setInt(1, orderItem.getQuantity());
           ps.setLong(2, orderItem.getCoffee().getId());
           ps.setLong(3, orderItem.getOrder().getId());
           ps.addBatch();
        }

        ps.executeUpdate();

        connection.close();
    }

    @Override
    public List<OrderItem> list() {

        return null;
    }

}
