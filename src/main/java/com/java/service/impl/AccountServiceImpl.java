package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.AccountDao;
import com.java.entity.Account;
import com.java.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDao accountDao;

	@Override
	public Account findById(String username) {
		// TODO Auto-generated method stub
		return accountDao.findById(username).get();
	}

	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return accountDao.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDao.findAll();
	}

	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		accountDao.deleteById(username);
	}
}
