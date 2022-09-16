package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.AccountDao;
import com.java.dao.AuthorityDao;
import com.java.entity.Account;
import com.java.entity.Authority;
import com.java.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDao authorityDao;
	@Autowired
	AccountDao accountDao;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDao.getAdministrators();
		return authorityDao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authorityDao.findAll();
	}

	@Override
	public Authority create(Authority authority) {
		// TODO Auto-generated method stub
		return authorityDao.save(authority);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		authorityDao.deleteById(id);
	}
}
