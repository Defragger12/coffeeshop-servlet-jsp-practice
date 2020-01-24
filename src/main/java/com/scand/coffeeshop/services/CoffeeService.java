package com.scand.coffeeshop.services;

import com.scand.coffeeshop.repository.CoffeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CoffeeService {

    private final CoffeeDao coffeeDao;

    @Autowired
    public CoffeeService(@Qualifier("sqlCoffeeDao") CoffeeDao coffeeDao) {

        this.coffeeDao = coffeeDao;
    }

    public void displayCoffees(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setAttribute("coffeeList", coffeeDao.list());
        request.getRequestDispatcher("/views/coffeeList.jsp").forward(request, response);
    }
}
