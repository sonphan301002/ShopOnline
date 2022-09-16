package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.service.OrderService;

@Controller
public class OrderControler {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser(); // user của người đăng nhập
		model.addAttribute("orders", orderService.findByUsername(username));
		
		return "order/list";
	}
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		model.addAttribute("order", orderService.findById(id));
		
		return "order/detail";
	}
}
