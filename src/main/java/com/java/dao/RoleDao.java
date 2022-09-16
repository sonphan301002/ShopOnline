package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.Role;

public interface RoleDao extends JpaRepository<Role, String>{

}
