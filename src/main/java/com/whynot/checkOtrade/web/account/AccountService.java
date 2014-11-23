/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.checkOtrade.web.account;


import com.whynot.checkOtrade.web.domain.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author nevezhinpavel
 */
@Service
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

	public Account findByEmail(String login){
		return accountRepository.findByEmail(login);
	}
	
	public List<Account> findAll(){
		return accountRepository.findAll();
	}
	
	public Account updateAccount(Account account) {

		return accountRepository.update(account);
	}
}
