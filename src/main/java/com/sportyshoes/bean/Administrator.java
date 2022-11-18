package com.sportyshoes.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Administrator")
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int adminId;
	String adminUserId;
	String adminPassword;
	
	
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Administrator(String adminUserId, String adminPassword) {
		super();
		this.adminUserId = adminUserId;
		this.adminPassword = adminPassword;
	}
	public String getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public int getAdminId() {
		return adminId;
	}
	
}
