package com.scand.coffeeshop.services;

import com.scand.coffeeshop.domain.Coffee;
import com.scand.coffeeshop.domain.Order;
import com.scand.coffeeshop.domain.OrderItem;
import com.scand.coffeeshop.repository.OrderDao;
import com.scand.coffeeshop.repository.OrderItemDao;
import com.scand.coffeeshop.to.CoffeeToConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private List<CoffeeToConfirm> pendingCoffeeOrder = new ArrayList<>();
    private BigDecimal totalOrderPrice;

    private final OrderDao orderDao;

    private final OrderItemDao orderItemDao;

    @Autowired
    public OrderService(
            @Qualifier("sqlOrderDao") OrderDao orderDao,
            @Qualifier("sqlOrderItemDao") OrderItemDao orderItemDao) {

        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
    }

    public void displayOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        parseOrderInfo(request, response);

        request.setAttribute("coffeeList", pendingCoffeeOrder);
        request.setAttribute("finalPrice", totalOrderPrice);

        request.getRequestDispatcher("/views/confirmOrder.jsp").forward(request, response);
    }

    public void displaySavedOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("orderList", orderDao.list());
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    public void finishOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Long orderId = orderDao.save(new Order(Collections.emptyList(), totalOrderPrice));

        List<OrderItem> orderItems = new ArrayList<>();
        for (CoffeeToConfirm pendingCoffee : pendingCoffeeOrder) {
            orderItems.add(
                    new OrderItem(
                            new Coffee(Long.valueOf(pendingCoffee.getCoffeeId())),
                            new Order(orderId),
                            Integer.valueOf(pendingCoffee.getTotalQuantity())
                    )
            );
        }

        orderItemDao.save(orderItems);

        pendingCoffeeOrder.clear();
        totalOrderPrice = BigDecimal.ZERO;

        String customerName = request.getParameter("customer_name");
        String customerPhone = request.getParameter("customer_phone");
        String customerAddress = request.getParameter("customer_address");

        response.sendRedirect("/views/congratulation.jsp");
    }

    private void parseOrderInfo(HttpServletRequest request, HttpServletResponse response) {

        String[] coffeeNames = request.getParameterValues("coffeeName");
        String[] coffeeDescriptions = request.getParameterValues("coffeeDescription");
        String[] coffeePrices = request.getParameterValues("coffeePrice");
        String[] coffeeIds = request.getParameterValues("coffeeId");
        String[] totalQuantities = request.getParameterValues("totalQuantity");
        String[] totalPrices = request.getParameterValues("totalPrice");

        BigDecimal totalPrice = new BigDecimal(0);

        List<CoffeeToConfirm> coffees = new ArrayList<>();
        for (int i = 0; i < totalQuantities.length; i++) {
            if (totalQuantities[i].equals("0")) {
                continue;
            }

            coffees.add(new CoffeeToConfirm(
                    coffeeIds[i],
                    coffeeNames[i],
                    coffeePrices[i],
                    coffeeDescriptions[i],
                    totalQuantities[i]
            ));

            totalPrice = totalPrice.add(new BigDecimal(totalPrices[i]));
        }

        pendingCoffeeOrder = coffees;
        totalOrderPrice = totalPrice;
    }
}
