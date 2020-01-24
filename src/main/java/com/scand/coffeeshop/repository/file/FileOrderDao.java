package com.scand.coffeeshop.repository.file;

import com.scand.coffeeshop.domain.Coffee;
import com.scand.coffeeshop.domain.Order;
import com.scand.coffeeshop.repository.OrderDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileOrderDao implements OrderDao {

    private static final String FILE_PATH = "C:\\Users\\kuzmianok\\IdeaProjects\\coffeeshop\\order.txt";

    private static Map<Long, Order> orderMap = new HashMap<>();

    static {
        try {
            prepopulateOrderMap();
        } catch (Exception ignored) {}
    }

    public Order get(Long id) {

        return orderMap.get(id);
    }

    public Long save(Order order) throws IOException {

        Long orderId = order.getId();

        orderMap.put(orderId, order);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

        oos.writeObject(list());
        oos.flush();
        oos.close();

        return orderId;
    }

    public List<Order> list() {

        return new ArrayList<>(orderMap.values());
    }

    private static void prepopulateOrderMap() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
        List<Order> orderList = (List<Order>) ois.readObject();
        ois.close();

        for (Order order: orderList) {
            orderMap.put(order.getId(), order);
        }
    }
}
