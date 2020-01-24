package com.scand.coffeeshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RootController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void root(HttpServletResponse response)
            throws IOException {

        response.sendRedirect("/coffeeList");
    }
}
