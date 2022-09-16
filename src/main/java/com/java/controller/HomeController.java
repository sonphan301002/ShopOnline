package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/index")
	public String list(Model model) {
		model.addAttribute("newPro", productService.findProductNew());
		model.addAttribute("salePro", productService.findSaleProduct());
		
		return "layout/main";
	}
}
