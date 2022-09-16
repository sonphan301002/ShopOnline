package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product p where p.category.id=?1")
	List<Product> findByCateId(String cid);
	
	@Query(value = "select top 4 * from products ORDER BY products.createdate desc", nativeQuery = true)
	List<Product> findProductNew();
	
	@Query(value = "select top 4 * from products ORDER BY products.price desc", nativeQuery = true)
	List<Product> findSaleProduct();

}
