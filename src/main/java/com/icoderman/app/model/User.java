package com.icoderman.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="fullname")
	private String fullName;

	@Column(name="email")
	private String email;

	private User() {
	}

	public User(Long id, String username, String password, String fullName, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}
}
