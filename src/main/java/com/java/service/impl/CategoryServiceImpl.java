package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CategoryDao;
import com.java.entity.Category;
import com.java.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.save(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.save(category);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		categoryDao.deleteById(id);
	}
}
