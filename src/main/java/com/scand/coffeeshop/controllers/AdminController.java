package com.scand.coffeeshop.controllers;

import com.scand.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminController {

    private final OrderService orderService;

    @Autowired
    public AdminController(OrderService orderService) {

        this.orderService = orderService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public void displayOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        orderService.displaySavedOrders(request, response);
    }
}