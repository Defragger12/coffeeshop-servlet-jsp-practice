package com.scand.coffeeshop.repository.sql;

import com.scand.coffeeshop.domain.Coffee;
import com.scand.coffeeshop.repository.CoffeeDao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SqlCoffeeDaoTest {

    @Test
    void save() throws Exception {
        CoffeeDao sqlCoffeeDao = new SqlCoffeeDao();
        sqlCoffeeDao.save(new Coffee(1l,"Name" , "Description", BigDecimal.valueOf(12)));
    }
}