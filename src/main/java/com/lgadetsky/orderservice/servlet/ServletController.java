package com.lgadetsky.orderservice.servlet;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.lgadetsky.orderservice.exception.OrderNotFoundException;
import com.lgadetsky.orderservice.model.Order;
import com.lgadetsky.orderservice.model.dto.MessageDTO;
import com.lgadetsky.orderservice.model.dto.OrderDTO;
import com.lgadetsky.orderservice.service.OrderService;

@WebServlet(value = "/servlet")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 8024790167396194706L;
	
	@Autowired
	private OrderService orderService;
	
	private static final String ID_NOT_FOUND = "Order with requested ID not found";
	private static final String BAD_REQ = "Bad request!";
	private static final String HTML_CREATED = "<html>" + "<h3>New order successfully created</h3> " + "</html>";
	private static final String HTML_UPDATED = "<html>" + "<h3>Order successfully updated</h3>" + "</html>";
	private static final String HTML_DELETED = "<html>" + "<h3>Order successfully deleted</h3>" + "</html>";
	
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
				 jaxbMarshaller.marshal(OrderDTO.of(orderService.findById(id)), out);
			 } catch(OrderNotFoundException e) {
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND, e);
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
			case CREATE:{
				
				orderService.create(Order.of(mes.getBody().getOrder()));
			
				out.println(HTML_CREATED);
				break;
			}
			case UPDATE:{
			
				try {
					orderService.update(Order.of(mes.getBody().getOrder()));
				} catch(OrderNotFoundException e) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQ, e);
				}	
				out.println(HTML_UPDATED);
				break;
			}

			case DELETE:{
				orderService.deleteById(mes.getBody().getOrder().getId());
				
				out.println(HTML_DELETED);
				break;
			}
			default:
				resp.sendError(400, BAD_REQ);
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
