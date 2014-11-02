/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.checkOtrade.web.ws;

import java.io.Serializable;

/**
 *
 * @author potapov
 */
public class LoginData implements Serializable{
	
	private String login;
	private String password;

	public LoginData() {
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
