package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.dao.OrderDAO;
import com.fpoly.dao.OrderDetailDAO;
import com.fpoly.entity.Order;
import com.fpoly.entity.OrderDetail;
import com.fpoly.service.OrderService;


@Service
@Component
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO ddao;
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type )
				.stream().peek(d->d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return order;
	}
	@Override
	public Order findById(Long id) {
	    Optional<Order> optionalOrder = dao.findById(id);
	    return optionalOrder.orElseThrow(() -> new RuntimeException("Order not found"));
	}
	@Override
	public Object findByUsername(String username) {
		return dao.findByUsername(username);
	}
	   @Override
	    public List<Order> findAll() {
	        return dao.findAll();
	    }

	    @Override
	    public void deleteById(Long id) {
	        dao.deleteById(id);
	    }
		@Override
		public Order save(Order order) {
			
			return dao.save(order);
		}

}
