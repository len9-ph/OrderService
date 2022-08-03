package com.lgadetsky.orderservice.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.service.OrderService;;

@WebServlet(value = "/servlet")
public class ServletController extends HttpServlet{
    
	private final OrderService orderService;

    public ServletController(OrderService orderService) {
        this.orderService = orderService;
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/xml");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        processRequest(req, resp);
        
		/* InputStream is = req.getInputStream(); */
        //Получаем id заказа из параметров запроса 
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.print(true);
        //Строим xml файл по полученному из базы pojo классу отображающему нужный заказ
        try {
        	JAXBContext jaxbContent = JAXBContext.newInstance(Order.class);
            Marshaller jaxbMarshaller = jaxbContent.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(orderService.findById(id), System.out);
        }
        catch(JAXBException e) {
        	e.printStackTrace();
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    
    
    

}
