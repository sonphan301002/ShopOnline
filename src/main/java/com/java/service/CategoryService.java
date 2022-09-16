package com.java.service;

import java.util.List;

import com.java.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category save(Category category);

	Category update(Category category);

	void delete(String id);

}
