package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart/list")
	public String list() {
		return "cart/list";
	}
}
