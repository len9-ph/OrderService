package com.lgadetsky.orderservice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lgadetsky.orderservice.service.OrderService;
import com.lgadetsky.orderservice.model.*;;

@WebServlet(value = "/servlet")
public class ServletController extends HttpServlet{
    private final OrderService orderService;

    public ServletController(OrderService orderService) {
        this.orderService = orderService;
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
        processRequest(req, resp);
        if(req.getParameter("id") != null) {
            //Выполняется получение пользователя по id
            Order order = orderService.findById(Integer.parseInt(req.getParameter("id")));
            PrintWriter writer = resp.getWriter();
            writer.println(req.getContentType());

            try{
                writer.println("<h2>id: " + order.getId() + "</h2>");
                writer.println("<h2>order status id: " + order.getOrderStatusId() + "</h2>");
                writer.println("<h2>Customer name: " + order.getCustomerName() + "</h2>");
                writer.println("<h2>Customer phone: " + order.getCustomerPhone() + "</h2>");
                writer.println("<h2>Customer comment: " + order.getCustomerComment() + "</h2>");
                LinkedList<OrderItem> items = (LinkedList<OrderItem>) order.getOrderItems();
                writer.println("<h2>Items:");
                for(OrderItem x : items)
                    writer.println(x.toString());
                writer.println("</h2>");
            } finally {
                writer.close();
            }
        }
        else{
            //Выполняется получение всех пользователей

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    
    
    

}
