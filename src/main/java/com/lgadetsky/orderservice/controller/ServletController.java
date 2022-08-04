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
import com.lgadetsky.orderservice.model.dto.OrderDTO;
import com.lgadetsky.orderservice.service.OrderService;;

@WebServlet(value = "/servlet")
public class ServletController extends HttpServlet {

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
		// Получаем id заказа из параметров запроса
		int id = Integer.parseInt(req.getParameter("id"));
		// Строим xml файл по полученному из базы pojo классу отображающему нужный заказ
		try {
			/*
			 * PrintWriter out = resp.getWriter(); JAXBContext jaxbContent =
			 * JAXBContext.newInstance(MessageDTO.class); Marshaller jaxbMarshaller =
			 * jaxbContent.createMarshaller();
			 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 * MessageDTO mes = new MessageDTO(); mes.setCommand("test"); mes.setBody(new
			 * OrderDTO(orderService.findById(id)));
			 * 
			 * jaxbMarshaller.marshal(mes, out);
			 */

			
			 PrintWriter out = resp.getWriter(); 
			 JAXBContext jaxbContent = JAXBContext.newInstance(Order.class); 
			 Marshaller jaxbMarshaller = jaxbContent.createMarshaller();
			 jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 jaxbMarshaller.marshal(orderService.findById(id), out);
			 } catch (JAXBException e) {
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
			out.print(mes);

			switch (mes.getCommand()) {
			case ("create"):
				orderService.create(mes.getBody().getOrder());
				break;

			case ("update"):
				orderService.update(mes.getBody().getOrder());
				break;

			case ("delete"):
				orderService.deleteById(mes.getBody().getOrder().getId());
				break;
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
