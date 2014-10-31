/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.ws;

import com.whynot.web.account.Account;
import com.whynot.web.account.AccountService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author potapov
 */
@RestController
public class LoginController {
	
	@Autowired
	AccountService us;
	
	
	static Map<String,Account> usermap = new HashMap<>();
	
	@RequestMapping(value = "rest/login", method = RequestMethod.POST)
	public @ResponseBody String login(LoginData data){
		String response = "null";
		System.out.println(data.getLogin()+" :: "+data.getPassword());
		Account acc = us.findByEmail(data.getLogin());
		System.out.println("acc :: "+acc);
		if(acc==null){return response;}
		System.out.println("acc :: "+ acc);
		if(acc.getPassword().equals(data.getPassword())){
			response = "zxxjgn97gj21r2982";
		}
		usermap.put(response, acc);
		return response;
	}
	
	@RequestMapping(value = "rest/login", method = RequestMethod.GET)
	public LoginData login1(){
		System.out.println("IN GET");
		LoginData item = new LoginData();
		item.setLogin("test");
		item.setPassword("test");
		return item;
	}
}
