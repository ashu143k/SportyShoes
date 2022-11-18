package com.sportyshoes.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userList")
@NamedQuery(name="userList.findAll", query="SELECT u FROM UserList u")
public class UserList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private String userName;
	
	private String userEmail;
	
	@OneToMany(mappedBy = "user")
	private List<PurchaseReport> purchaseReport;
	
	public UserList(String userName, String userEmail) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
	}

	public UserList() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserId() {
		return userId;
	}


	
}
