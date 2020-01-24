package com.scand.coffeeshop.repository;

import com.scand.coffeeshop.domain.Coffee;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface CoffeeDao {

    Coffee get(Long id) throws Exception;

    Long save(Coffee coffee) throws Exception;

    Collection<Coffee> list() throws Exception;
}