package com.java.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Product;
import com.java.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid) {
		if (cid.isPresent()) { // nếu có
			List<Product> list = productService.findByCateId(cid.get());
			model.addAttribute("products", list);
		} else {
			model.addAttribute("products", productService.findAll());
		}

		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);

		return "product/detail";
	}
}
