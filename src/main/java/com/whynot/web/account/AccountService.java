/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.account;


import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author nevezhinpavel
 */
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(String email, String password, String role) {
		Account account = new Account();
		
		account.setEmail(email);
		account.setPassword(password);
		account.setRole(role);
		
		Account result = accountRepository.create(account);
		return result;
	}

	public boolean deleteAccount(Account account) {

		return accountRepository.delete(account);
	}

	public Account updateAccount(Account account) {

		return accountRepository.update(account);
	}
}
