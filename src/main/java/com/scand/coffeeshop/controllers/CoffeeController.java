package com.scand.coffeeshop.controllers;

import com.scand.coffeeshop.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CoffeeController{

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {

        this.coffeeService = coffeeService;
    }

    @RequestMapping(value = "/coffeeList", method = RequestMethod.GET)
    public void displayCoffees(HttpServletRequest request, HttpServletResponse response) {

        try {
            coffeeService.displayCoffees(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
