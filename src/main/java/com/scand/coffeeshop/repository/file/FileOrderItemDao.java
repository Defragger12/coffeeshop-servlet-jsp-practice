package com.scand.coffeeshop.repository.file;

import com.scand.coffeeshop.domain.OrderItem;
import com.scand.coffeeshop.repository.OrderItemDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileOrderItemDao implements OrderItemDao {

    private static final String FILE_PATH = "C:\\Users\\kuzmianok\\IdeaProjects\\coffeeshop\\orderItem.txt";

    private static Map<Long, OrderItem> orderItemMap = new HashMap<>();

    public void save(List<OrderItem> orderItems) throws IOException {

        for (OrderItem orderItem : orderItems) {
            orderItemMap.put(orderItem.getId(), orderItem);
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

        oos.writeObject(list());
        oos.flush();
        oos.close();
    }

    @Override
    public List<OrderItem> list() {

        return new ArrayList<>(orderItemMap.values());
    }


    private static void prepopulateOrderItemMap() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
        List<OrderItem> orderItemList = (List<OrderItem>) ois.readObject();
        ois.close();

        for (OrderItem orderItem: orderItemList) {
            orderItemMap.put(orderItem.getId(), orderItem);
        }
    }
}
