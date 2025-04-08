package com.fpoly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fpoly.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	 Order findById(Long id);
	Object findByUsername(String username);
	
    List<Order> findAll();

    void deleteById(Long id);

}
