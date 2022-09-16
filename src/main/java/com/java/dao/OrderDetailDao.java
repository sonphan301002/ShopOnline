package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long>{

}
