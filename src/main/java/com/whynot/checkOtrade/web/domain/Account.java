package com.whynot.checkOtrade.web.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Сущность пользователя приложения.
 *
 * @author Midikko
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email = "";

	@Column(name = "name")
	private String name = "";

	@Column(name = "avatar")
	private String avatar = "";

	@Column(name = "password", nullable = false)
	private String password = "";

	@Column(name = "role", nullable = false)
	private String role = "ROLE_USER";

	@OneToMany(targetEntity = Payment.class, mappedBy = "account", cascade = CascadeType.ALL)
	private Set payments;

	public Account() {
	}

	public Account(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set getPayments() {
		return payments;
	}

	public void setPayments(Set payments) {
		this.payments = payments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
