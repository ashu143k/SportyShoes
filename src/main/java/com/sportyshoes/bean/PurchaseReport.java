package com.sportyshoes.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchaseReport")
@NamedQuery(name="PurchaseList.findAll", query="SELECT p FROM PurchaseReport p")
public class PurchaseReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;
	private Date purchaseDate;

	@ManyToOne
	private UserList user;
	
	@OneToOne
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Products products;
	
	

	public UserList getUser() {
		return user;
	}

	public void setUser(UserList user) {
		this.user = user;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public PurchaseReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
}
