package com.scand.coffeeshop.repository.file;

import com.scand.coffeeshop.domain.Coffee;
import com.scand.coffeeshop.repository.CoffeeDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileCoffeeDao implements CoffeeDao {

    private static final String FILE_PATH = "C:\\Users\\kuzmianok\\IdeaProjects\\coffeeshop\\coffee.txt";

    private Map<Long, Coffee> coffeeMap = new HashMap<>();

    public FileCoffeeDao() throws IOException, ClassNotFoundException {
        prepopulateCoffeeMap();
    }

    public Coffee get(Long id) {

        return coffeeMap.get(id);
    }

    public Long save(Coffee coffee) throws Exception {

        Long coffeeId = coffee.getId();

        coffeeMap.put(coffeeId, coffee);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

        oos.writeObject(list());
        oos.flush();
        oos.close();

        return coffeeId;
    }

    public List<Coffee> list() {

        return new ArrayList<>(coffeeMap.values());
    }

    private void prepopulateCoffeeMap() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
        List<Coffee> coffeeList = (List<Coffee>) ois.readObject();
        ois.close();

        for (Coffee coffee : coffeeList) {
            coffeeMap.put(coffee.getId(), coffee);
        }
    }
}
