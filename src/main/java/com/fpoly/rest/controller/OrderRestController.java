package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fpoly.entity.Order;
import com.fpoly.service.OrderService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	@PostMapping()
	public Order create (@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	
	@GetMapping("{id}")
	public Order getOrder(@PathVariable("id") Long id) {
	    return orderService.findById(id);
	}

	
	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.findAll();
	}

	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable("id") Long id) {
	   orderService.deleteById(id);
	}
	
	
}