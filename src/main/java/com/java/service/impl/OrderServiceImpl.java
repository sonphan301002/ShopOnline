package com.java.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dao.OrderDao;
import com.java.dao.OrderDetailDao;
import com.java.entity.Order;
import com.java.entity.OrderDetail;
import com.java.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDao orderDao;
	@Autowired
	OrderDetailDao orderDetailDao;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper(); // để chuyển đổi Json thành các đối tượng cần thiết
		
		Order order = mapper.convertValue(orderData, Order.class); // lấy 1 phần cho vào Order
		orderDao.save(order);
		
		TypeReference<List<OrderDetail>> typeReference = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), typeReference) // đọc và lấy list orderDetail
			.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailDao.saveAll(details);
		
		return order;
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return orderDao.findByUsername(username);
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return orderDao.findById(id).get();
	}
}
