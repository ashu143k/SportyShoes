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
@Table (name="ProductCategory")
@NamedQuery(name="ProductCategory.findAll", query="SELECT pc FROM ProductCategory pc")
public class ProductCategory {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int productCategoryID;
	
	private String productCategoryName;

	@OneToMany(mappedBy = "productCategory")
	private List<Products> products;
	
	protected ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected ProductCategory(String productCategoryName) {
		super();
		this.productCategoryName = productCategoryName;
	}
	public int getProductCategoryID() {
		return productCategoryID;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void addProducts(Products product) {
		this.products.add(product);
	}
	public void removeProducts(Products product) {
		this.products.remove(product);
	}
	
	
}
