package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.Category;

public interface CategoryDao extends JpaRepository<Category, String>{

}
