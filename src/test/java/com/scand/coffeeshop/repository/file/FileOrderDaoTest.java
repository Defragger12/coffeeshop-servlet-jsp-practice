package com.scand.coffeeshop.repository.file;

import com.scand.coffeeshop.domain.Coffee;
import com.scand.coffeeshop.domain.Order;
import com.scand.coffeeshop.domain.OrderItem;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileOrderDaoTest {

    @Test
    void save() throws IOException {

        FileOrderDao dao = new FileOrderDao();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem());

        dao.save(new Order(orderItems, BigDecimal.valueOf(1)));
        dao.save(new Order(orderItems, BigDecimal.valueOf(1)));
        dao.save(new Order(orderItems, BigDecimal.valueOf(1)));

        assertEquals(dao.list().size(), 3);
    }

    @Test
    void list() {
        FileOrderDao dao = new FileOrderDao();
    }
}