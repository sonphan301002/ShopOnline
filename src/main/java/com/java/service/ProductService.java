package com.java.service;

import java.util.List;

import com.java.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCateId(String cid);

	List<Product> findProductNew();

	List<Product> findSaleProduct();

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
	
}
