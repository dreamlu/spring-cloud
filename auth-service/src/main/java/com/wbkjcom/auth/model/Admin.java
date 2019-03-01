package com.wbkjcom.auth.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Admin implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account", unique = true, columnDefinition = "varchar(20) default ''")
	private String account;

	@Column(name = "password", columnDefinition = "varchar(50) default ''")
	private String password;


	public Admin() {
	}

	public Admin(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
