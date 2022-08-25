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

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.lgadetsky.orderservice.exception.OrderNotFoundException;
import com.lgadetsky.orderservice.model.dto.MessageDTO;
import com.lgadetsky.orderservice.repository.mapper.Mapper;
import com.lgadetsky.orderservice.service.OrderService;;

@WebServlet(value = "/servlet")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 8024790167396194706L;
	
	private final OrderService orderService;
    private final Mapper mapper;

    public ServletController(OrderService orderService, Mapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/xml");
		
		int id = Integer.parseInt(req.getParameter("id"));
		try {

			 PrintWriter out = resp.getWriter(); 
			 JAXBContext jaxbContent = JAXBContext.newInstance(MessageDTO.class); 
			 Marshaller jaxbMarshaller = jaxbContent.createMarshaller();
			 jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 try {
				 jaxbMarshaller.marshal(mapper.toDTO(orderService.findById(id)), out);
			 } catch(OrderNotFoundException e) {
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with requested ID not found", e);
			 }
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
				
				orderService.create(mapper.dtoStringToOrder(mes.getBody().getOrder()));
			
				out.println("<html>"
						+ "<h3>New order successfully created</h3> "
						+ "</html>");
				break;
			}
			case ("update"):{
			
				try {
					orderService.update(mapper.toOrder(mes.getBody().getOrder()));
				} catch(OrderNotFoundException e) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct Order id", e);
				}
				orderService.update(mapper.toOrder(mes.getBody().getOrder()));
				
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
			default:
				resp.sendError(400, "Bad request!");
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
