/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.domain;

import com.whynot.web.account.Account;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Сущность для описания операций с балансом пользователя
 * @author potapov
 */
@Entity
@Table(name = "payment")
public class Payment {
	
	public enum Type {

		DEBIT, CREDIT;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Column(name = "value", nullable = false)
	private long value;
	
	@ManyToOne(targetEntity = Account.class, optional = false)
        @JoinColumn(name = "account_id")
	private Account account;

	public Payment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Payment(Type type, long value, Account account) {
        this.type = type;
        this.value = value;
        this.account = account;
    }
	
	
	
}
