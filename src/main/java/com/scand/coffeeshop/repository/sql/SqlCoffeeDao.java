package com.scand.coffeeshop.repository.sql;

import com.scand.coffeeshop.domain.Coffee;
import com.scand.coffeeshop.repository.CoffeeDao;
import com.scand.coffeeshop.repository.sql.util.SqlConnectionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SqlCoffeeDao implements CoffeeDao {

    private Map<Long, Coffee> coffeeMap = new HashMap<>();


    public SqlCoffeeDao() throws Exception {
        prepopulateCoffeeMap();
    }

    @Override
    public Coffee get(Long id) throws Exception {

        Connection connection = SqlConnectionManager.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM coffee WHERE id = ?"
        );
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Coffee(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("price")
            );
        }

        connection.close();

        return null;
    }

    @Override
    public Long save(Coffee coffee) throws Exception {

        Connection connection = SqlConnectionManager.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO coffee (name, description, price) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, coffee.getName());
        ps.setString(2, coffee.getDescription());
        ps.setBigDecimal(3, coffee.getPrice());

        ps.executeUpdate();

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                coffee.setId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating coffee failed, no ID obtained.");
            }
        }

        coffeeMap.put(coffee.getId(), coffee);

        connection.close();

        return coffee.getId();
    }

    @Override
    public Collection<Coffee> list() throws Exception {

        return coffeeMap.values();
    }

    private void prepopulateCoffeeMap() throws Exception {

        Connection connection = SqlConnectionManager.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM coffeeshop.coffee");
        while (rs.next()) {
            coffeeMap.put(
                    rs.getLong(1),
                    new Coffee(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getBigDecimal("price")
                    )
            );
        }
        connection.close();
    }
}
