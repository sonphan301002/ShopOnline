package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.RoleDao;
import com.java.entity.Role;
import com.java.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao roleDao;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
}
