package com.scand.coffeeshop.controllers;

import com.scand.coffeeshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class OrderController{

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public void displayOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        orderService.displayOrder(request, response);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public void finishOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            orderService.finishOrder(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
