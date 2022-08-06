package com.lgadetsky.orderservice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.dto.MessageDTO;
import com.lgadetsky.orderservice.service.OrderService;;

@WebServlet(value = "/servlet")
public class ServletController extends HttpServlet {

	private final OrderService orderService;

	public ServletController(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/xml");
		
		// Получаем id заказа из параметров запроса
		
		int id = Integer.parseInt(req.getParameter("id"));
		// Строим xml файл по полученному из базы pojo классу отображающему нужный заказ
		try {

			 PrintWriter out = resp.getWriter(); 
			 JAXBContext jaxbContent = JAXBContext.newInstance(Order.class); 
			 Marshaller jaxbMarshaller = jaxbContent.createMarshaller();
			 jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 jaxbMarshaller.marshal(orderService.findById(id), out);
			 } 
		catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream is = req.getInputStream();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MessageDTO.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MessageDTO mes = (MessageDTO) jaxbUnmarshaller.unmarshal(is);

			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html");

			switch (mes.getCommand()) {
			case ("create"):{
				orderService.create(mes.getBody().getOrder());
			
				out.println("<html>"
						+ "<h3>New order successfully created</h3> "
						+ "</html>");
				break;
			}
			case ("update"):{
			
				int id = Integer.parseInt(req.getParameter("id"));
				orderService.update(id ,mes.getBody().getOrder());
				
				out.println("<html>"
						+ "<h3>Order successfully updated</h3>"
						+ "</html>");
				break;
			}

			case ("delete"):{
				orderService.deleteById(mes.getBody().getOrder().getId());
				
				out.println("<html>"
						+ "<h3>Order successfully deleted</h3>"
						+ "</html>");
				break;
			}
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
