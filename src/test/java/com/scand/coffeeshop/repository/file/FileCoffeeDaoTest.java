package com.scand.coffeeshop.repository.file;

import com.scand.coffeeshop.domain.Coffee;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileCoffeeDaoTest {

    @Test
    void save() throws Exception {

        FileCoffeeDao dao = new FileCoffeeDao();
        dao.save(new Coffee("Name" , "Description", BigDecimal.valueOf(12)));
        dao.save(new Coffee("Name2" , "Description2", BigDecimal.valueOf(5)));
        dao.save(new Coffee("Name3" , "Description3", BigDecimal.valueOf(7)));

        assertEquals(dao.list().size(), 3);
    }
}