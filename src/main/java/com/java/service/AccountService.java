package com.java.service;

import java.util.List;

import com.java.entity.Account;

public interface AccountService {

	Account findById(String username);

	Account save(Account account);

	List<Account> getAdministrators();

	List<Account> findAll();

	Account create(Account account);

	Account update(Account account);

	void delete(String username);

}
