package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.ProductDao;
import com.java.entity.Product;
import com.java.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return productDao.findById(id).get();
	}

	@Override
	public List<Product> findByCateId(String cid) {
		// TODO Auto-generated method stub
		return productDao.findByCateId(cid);
	}

	@Override
	public List<Product> findProductNew() {
		// TODO Auto-generated method stub
		return productDao.findProductNew();
	}

	@Override
	public List<Product> findSaleProduct() {
		// TODO Auto-generated method stub
		return productDao.findSaleProduct();
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productDao.deleteById(id);
	}
}
