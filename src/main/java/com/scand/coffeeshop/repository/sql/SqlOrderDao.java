package com.scand.coffeeshop.repository.sql;

import com.scand.coffeeshop.domain.Order;
import com.scand.coffeeshop.repository.OrderDao;
import com.scand.coffeeshop.repository.sql.util.SqlConnectionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SqlOrderDao implements OrderDao {

    private Map<Long, Order> orderMap = new HashMap<>();

    public SqlOrderDao() throws Exception {
        prepopulateOrderMap();
    }

    @Override
    public Order get(Long id) throws Exception {
        
        Connection connection = SqlConnectionManager.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM `order` WHERE id = ?"
        );
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Order(rs.getLong("id"), rs.getBigDecimal("price"));
        }

        connection.close();

        return null;
    }

    @Override
    public Long save(Order order) throws Exception {

        Connection connection = SqlConnectionManager.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO `order` (price) VALUES (?)", Statement.RETURN_GENERATED_KEYS
        );
        ps.setBigDecimal(1, order.getPrice());

        ps.executeUpdate();

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        }

        connection.close();

        orderMap.put(order.getId(), order);

        return order.getId();
    }

    @Override
    public List<Order> list() {

        return new ArrayList<>(orderMap.values());
    }

    private void prepopulateOrderMap() throws Exception {

        Connection connection = SqlConnectionManager.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM coffeeshop.order");
        while (rs.next()) {
            orderMap.put(
                    rs.getLong(1),
                    new Order(
                            rs.getLong("id"), rs.getBigDecimal("price")
                    )
            );
        }
        connection.close();
    }
}
